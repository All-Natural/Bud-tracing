package rest;

public class RestOk extends GenalRestResp
{
	public RestOk(int errorcode, String errormsg, String detail, Object data)
	{
		setErrcode(errorcode);
		setErrmsg(errormsg);
		setErrdetail(detail);
		setData(data);
	}
	
	public RestOk(int errorcode, String errormsg, Object data)
	{
		this(errorcode, errormsg, null, data);
	}
	
	public RestOk(int errorcode, String errormsg)
	{
		this(errorcode, errormsg, null, null);
	}
	
	public RestOk(Object data)
	{
		this(200, "ok", null, data);
	}
	
	public RestOk()
	{
		this(200, "ok", null, null);
	}
}
