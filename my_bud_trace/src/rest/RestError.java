package rest;

public class RestError extends GenalRestResp
{
	
	public RestError(int errorcode, String errormsg, String detail, Object data)
	{
		setErrcode(errorcode);
		setErrmsg(errormsg);
		setErrdetail(detail);
		setData(data);
	}
	
	public RestError(int errorcode, String errormsg, String detail)
	{
		this(errorcode, errormsg, detail, null);
	}
	
	public RestError(int errorcode, String errormsg)
	{
		this(errorcode, errormsg, null, null);
	}
	
	public RestError(String errormsg)
	{
		this(-1, errormsg, null, null);
	}
	
	public RestError()
	{
		this(-1, "error", null, null);
	}
}
