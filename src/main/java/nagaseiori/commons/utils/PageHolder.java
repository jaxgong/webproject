package nagaseiori.commons.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用于保存分页信息和负责分页逻辑运算的类。<br />
 * <b>这个类不是线程安全的</b>
 * </p>
 * 
 * 使用方法： <br />
 * PageHolder&lt;Person&gt; pList=new PageHolder&lt;Person&gt;();<br />
 * pHolder.setPageIndex();//当前页的页码<br />
 * pHolder.setPageSize();//每页的大小<br />
 * <br />
 * pHolder.setRowCount(dao.count(...));//获取数据库里的总记录数<br />
 * pHolder.setList(dao.list(...));//从数据库获取当前页的数据<br />
 * <br />
 * pHolder.addParam("category","5");//设置其他的url参数<br />
 * request.setAttribute("pageHolder",pHolder);//放入request<br />
 * 至此，PageHolder的数据填充就完成了，下一步是在JSP页面内做显示<br />
 * 
 */
public class PageHolder<E> implements Serializable {

	private static final long serialVersionUID = -5748653585357195099L;

	protected static final transient int DEFAULT_MAX_PAGE_SIZE = 5000;

	protected static final transient int DEFAULT_PAGE_SIZE = 25;

	protected static final transient String DEFAULT_PARAM_ENCODING = "utf-8";

	/**
	 * 当前页码
	 */
	private int pageIndex;
	
	/**
	 * 当前页码（页面传入字符时转换用）
	 */
	private String pageIndexStr; 

	/**
	 * 页大小
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 页大小是否可以重设
	 */
	private boolean pageSizeResetAble = true;

	/**
	 * 最大页大小
	 */
	private int maxPageSize = DEFAULT_MAX_PAGE_SIZE;

	/**
	 * 页数量
	 */
	private int pageCount;

	/**
	 * 行数量
	 */
	private long rowCount;
	
	/**
	 * 最大行数量
	 */
	private long maxRowCount;

	/**
	 * 数据列表
	 */
	private List<E> list;

	private String paramEncoding = DEFAULT_PARAM_ENCODING;

	private List<Param> paramList = new ArrayList<Param>();

	private String pageIndexKey = "pageIndex";
	
	private String pageIndexKeyStr = "pageIndexStr";
	
//	private String pageIndexKey = "pageHolder.pageIndex";
//	
//	private String pageIndexKeyStr = "pageHolder.pageIndexStr";
	
	public PageHolder(){}
	
	/**
	 * @param pageSize
	 *            页大小
	 */
	public PageHolder(int pageSize) {
		this(pageSize, false);
	}
	
	/**
	 * @param pageSize
	 *            页大小
	 */
	public PageHolder(int pageSize, boolean pageSizeResetAble) {
		this.pageSize = pageSize;
		this.pageSizeResetAble = pageSizeResetAble;
	}

	/**
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            页大小
	 */
	public PageHolder(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
		this.pageSize = pageSize;
	}

	/**
	 * @return pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return rowCount
	 */
	public long getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 *            要设置的 rowCount
	 * @throws Exception
	 */
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		if(maxRowCount > 0 && this.rowCount > maxRowCount){
			this.rowCount = maxRowCount;
		}
		int pageSize = getPageSize();
		try {
			pageCount = (int) ((this.rowCount - 1 + pageSize) / pageSize);
		} catch (ArithmeticException e) {
			throw new RuntimeException("Property pageSize has not set into PageHolder.");
		}
		if (pageCount > 0 && pageIndex > pageCount) {
			pageIndex = pageCount;
		}
	}

	/**
	 * @return pageIndex
	 */
	public int getPageIndex() {
		return pageIndex < 1 ? 1 : pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getPageIndexStr() {
		return pageIndexStr;
	}

	public void setPageIndexStr(String pageIndexStr) {
		try{
			int pageIndex_stemp = Integer.parseInt(pageIndexStr); 
			this.pageIndex = pageIndex_stemp;
		}catch(Exception e){
			
		}
	}

	public String getPageIndexKeyStr() {
		return pageIndexKeyStr;
	}

	public void setPageIndexKeyStr(String pageIndexKeyStr) {
		this.pageIndexKeyStr = pageIndexKeyStr;
	}

	public void setPageSize(int pageSize) {
		if (pageSizeResetAble) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		if (pageSize > maxPageSize) {
			return maxPageSize;
		} else {
			return pageSize;
		}
	}

	public int getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(int maxPageSize) {
		this.maxPageSize = maxPageSize;
	}

	/**
	 * @return list
	 */
	public List<E> getList() {
		if (list == null) {
			return new ArrayList<E>();
		}
		return list;
	}

	/**
	 * @param list
	 *            要设置的 list
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * @return paramEncoding
	 */
	public String getParamEncoding() {
		return paramEncoding;
	}

	/**
	 * @param paramEncoding
	 *            要设置的 paramEncoding
	 */
	public void setParamEncoding(String paramEncoding) {
		this.paramEncoding = paramEncoding;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value) {
		Param p = new Param(key, value == null ? "" : value.toString());
		boolean flag = true;
		for (Param m : paramList) {
			if (m.key.equals(p.key) && m.value.equals(p.value)) {
				flag = false;
				break;
			}
		}
		
		if (flag) {
			paramList.add(p);
		}

	}
	
	/**
	 * 
	 * @param key
	 */
	public void removeParam(String key) {
		for (Param m : paramList) {
			if (m.key.equals(key)) {
				paramList.remove(m);
				break;
			}
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void removeParam(String key, Object value) {
		Param p = new Param(key, value == null ? "" : value.toString());
		for (Param m : paramList) {
			if (m.key.equals(p.key) && m.value.endsWith(p.value)) {
				paramList.remove(m);
				break;
			}
		}
	}

	/**
	 * 当使用removeParam报错时可使用此方法
	 * 迭代过程中删除节点不会报java.util.ConcurrentModificationException
	 * @param key
	 */
	public void removeParamModify(String key){
		for(Iterator<Param> i = paramList.iterator();i.hasNext();){
			Param m  = i.next();
			if(m.key.equals(key)){
				i.remove();
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 */
	public void addAllParam(HttpServletRequest request){
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = (String)names.nextElement();
			if(name.indexOf(pageIndexKey) >= 0){
				continue;
			}
			String[] values = request.getParameterValues(name);
			for (String value : values) {
				addParam(name, value);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getParams() {
		StringBuilder sb = new StringBuilder();
		try {
			for (Param p : paramList) {
				sb.append("&amp;");
				sb.append(URLEncoder.encode(p.key, paramEncoding));
				sb.append('=');
				sb.append(URLEncoder.encode(p.value, paramEncoding));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}

	/**
	 * 取当前页的第一行数据在数据库中的行号，在操作数据库的时候有用
	 * 
	 * @return
	 */
	public int getRowOffset() {
		return (getPageIndex() - 1) * getPageSize();
	}

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return getPageIndex() <= 1 ? true : false;
	}

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage() {
		return getPageIndex() >= getPageCount() ? true : false;
	}

	public String getPageIndexKey() {
		return pageIndexKey;
	}

	public void setPageIndexKey(String pageIndexKey) {
		this.pageIndexKey = pageIndexKey;
	}

	private class Param {

		public String key;

		public String value;

		public Param(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public long getMaxRowCount() {
		return maxRowCount;
	}

	public void setMaxRowCount(long maxRowCount) {
		this.maxRowCount = maxRowCount;
	}

	public static void main(String[] args) {
		/*PageHolder<String> p = new PageHolder<String>(3, 4);
		p.addParam("1", "中文");
		p.addParam("2", "22");
		p.addParam("3", "33");
		System.out.println(p.getParams());
		p.setRowCount(40);
		System.out.println(p.getPageCount());*/
	}
}
