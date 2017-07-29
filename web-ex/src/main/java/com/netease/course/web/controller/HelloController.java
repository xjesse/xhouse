package com.netease.course.web.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.netease.course.dao.Buycontent;
import com.netease.course.dao.Content;
import com.netease.course.dao.User;
import com.netease.course.utils.Imagetool;
import com.netease.course.utils.Jdbctool;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


@Controller
public class HelloController {
	private static User user;
	private static List<Content> Contentlist;
	private static byte[] imagec;
	
	
	@RequestMapping(value="/logins")//登录界面
	public String login()throws IOException {
		return"login";
	}
		
	@RequestMapping(value="/login")
	@ResponseBody
    public void doPost(HttpServletRequest request, 
    		HttpServletResponse response,
    		Object responseObject,
    		ModelMap map)  
            throws ServletException, IOException {  
		
		user=null;//初始化
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		response.setCharacterEncoding("UTF-8");
		
		Jdbctool jtl=new Jdbctool();//拿工具类
		user=jtl.getuser(userName, password);
		
		String json="";
		if(!(user==null)){	
		json="{\"result\":\"true\",\"code\":\"200\"}";
		}
		else{
		json="{\"result\":\"用户密码错误\",\"code\":\"500\"}";
		}	
		try {
			response.getWriter().print(json);;
			response.getWriter().flush();;
			response.getWriter().close();;
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
	
	
	@RequestMapping(value="/true")
	public void hom(HttpServletRequest request,
	HttpServletResponse response,
	ModelMap map	)
			throws IOException {
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();
		byte[] a=getRequestPostBytes(request);
	    response.setContentType("img/jpeg");  
	    response.setCharacterEncoding("utf-8");  	
        try {           
            OutputStream outputStream=response.getOutputStream();  
            InputStream in=new ByteArrayInputStream(imagec);  
              
            int len=0;  
            byte[]buf=new byte[1024];  
            while((len=in.read(buf,0,1024))!=-1){  
                outputStream.write(buf, 0, len);  
            }  
            outputStream.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	@RequestMapping(value="/image1")
	public void showimage(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap map)
			throws IOException {
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();

	    response.setContentType("img/jpeg");  
	    response.setCharacterEncoding("utf-8");  
		int id=0;
		String s=request.getParameter("id");//通过传递type改变样式
		if(!(s==null)){
			id=Integer.parseInt(s);
		}	  
        try {  
            OutputStream outputStream=response.getOutputStream();  
            InputStream in=new ByteArrayInputStream(Contentlist.get(id-1).geticon());  
              
            int len=0;  
            byte[]buf=new byte[1024];  
            while((len=in.read(buf,0,1024))!=-1){  
                outputStream.write(buf, 0, len);  
            }  
            outputStream.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	

	@RequestMapping(value="/home")
	public ModelAndView homd(HttpServletRequest request,
			ModelMap map)
			throws IOException {
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();
		int type=0;
		String s=request.getParameter("type");//通过传递type改变样式
		if(!(s==null)){
			type=Integer.parseInt(s);
		}
		
		map.addAttribute("listType", type);//传入listType参数
		ModelAndView mv = new ModelAndView();
        mv.setViewName("index");     
        mv.addObject("user",user);
        mv.addObject("productList",Contentlist);
        return mv;
	}

	
	@RequestMapping(value="/settleAccount")
	public ModelAndView settleAccount(ModelMap map)
			throws IOException {
		Imagetool imt=new Imagetool();//创建图片处理工具
		Jdbctool  jdl=new Jdbctool();
		ModelAndView mv = new ModelAndView();
        mv.setViewName("settleAccount");     
        mv.addObject("user",user);
        mv.addObject("buyList",Contentlist);         
        return mv;
	}
	
	
	@RequestMapping(value="/show")
	public ModelAndView show(HttpServletRequest request,
			ModelMap map)
			throws IOException {
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();
		String id=request.getParameter("id");
		int	iid=Integer.parseInt(id)-1;	
		Contentlist.get(iid).setbuyNum(0);//buynum进来设定都是0
		Contentlist.get(iid).setsummary(Contentlist.get(iid).getabstract1());//概述
		System.out.println(iid);
		byte[] b=new byte[1024];
		b=Contentlist.get(iid).gettext();
		String a=new String(b);//详细描述
		
		Contentlist.get(iid).setdetail(a);		
		ModelAndView mv = new ModelAndView();
        mv.setViewName("show");
        mv.addObject("user",user);
        mv.addObject("product",Contentlist.get(iid));

        return mv;
	}	
	
	@RequestMapping(value="/buy")
	@ResponseBody
    public void doPostbuy(HttpServletRequest request, 
    		HttpServletResponse response,
    		Object responseObject,
    		ModelMap map)  
            throws ServletException, IOException {  
		Jdbctool  jdl=new Jdbctool();
		String ab=getRequestPostStr(request);
		System.out.println(ab);
		
		JSONArray jsonArray = JSONArray.fromObject(ab);
		
		System.out.println(jsonArray.size());	
		  try{
			  for (int i=0;i<jsonArray.size();i++){
	JSONObject myjObject = jsonArray.getJSONObject(i);
	
	int id=Integer.parseInt(myjObject.getString("id"));//商品id
	int num=Integer.parseInt(myjObject.getString("number"));
	System.out.println(i);
	int userid=user.getId();//用户id
	int price =Contentlist.get(id-1).getprice();//价格
	Date date = new Date();
	long unixTimestamp = date.getTime();
	jdl.updatetrx(id, userid, price, unixTimestamp, num);
     }			  	  
	 }
	  catch (JSONException e){	 	  
		  System.out.println(e);
     } 
		String json="";		
		json="{\"result\":\"true\",\"code\":\"200\"}";
		try { 
		    response.getWriter().print(json);;
			response.getWriter().flush();;
			response.getWriter().close();;
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
	

	@RequestMapping(value="/account")
	public ModelAndView account(
			ModelMap map
			)
			throws IOException {
		Jdbctool  jdl=new Jdbctool();
		
		List<Buycontent> buyList=jdl.getBuycontentlist();
		
		for (int i=0;i<buyList.size();i++){
			int j=buyList.get(i).getcontentid()-1;

			buyList.get(i).setimage(Contentlist.get(j).getimage());
			buyList.get(i).settitle(Contentlist.get(j).gettitle());
		}

		ModelAndView mv = new ModelAndView();
        mv.setViewName("account");
        mv.addObject("user",user);
        mv.addObject("buyList",buyList);

        return mv;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, 
			ModelMap map
			)
			throws IOException {
		user=null;
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();
		int type=0;
		String s=request.getParameter("type");//通过传递type改变样式
		if(!(s==null)){
			type=Integer.parseInt(s);
		}		
		map.addAttribute("listType", type);//传入listType参数
	
		ModelAndView mv = new ModelAndView();
        mv.setViewName("index");        
        mv.addObject("user",user);
        mv.addObject("productList",Contentlist);
        return mv;
				}

	@RequestMapping(value="/public")
	public ModelAndView publicpage(
			ModelMap map
			)
			throws IOException {
		imagec=null;//进入页面时初始化
		ModelAndView mv = new ModelAndView();
        mv.setViewName("public");        
        mv.addObject("user",user);
        return mv;
				}
	
	
	@RequestMapping(value="/upload")
	@ResponseBody
    public void doPostpic(
    		@RequestParam("file") MultipartFile tmpFile,
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		Object responseObject,
    		ModelMap map)  
            throws Exception { 
        byte[] imagebyte =new byte[1024];
        if (tmpFile != null) {
            // 获取物理路径
            String targetDirectory = request.getSession().getServletContext().getRealPath("/image");
            System.out.println(targetDirectory);
            String tmpFileName = tmpFile.getOriginalFilename(); // 上传的文件名
            System.out.println(tmpFileName);
            int dot = tmpFileName.lastIndexOf('.');
            String ext = "";  //文件后缀名
            if ((dot > -1) && (dot < (tmpFileName.length() - 1))) {
                ext = tmpFileName.substring(dot + 1);
            }

            if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)) {
 
                InputStream a=tmpFile.getInputStream();       
                imagec= readStream(a);//流转换成byte[]              
            }
        }		
		String json="";
		Imagetool itl=new Imagetool();
		itl.byte2image(imagec, "C:/Users/Administrator/Desktop/图片素材库/表情包/g.jpg");		
		json="{\"result\":\"true\",\"code\":\"200\"}";
		try 
		{
			response.getWriter().print(json);;
			response.getWriter().flush();;
			response.getWriter().close();;
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
	

	@RequestMapping(value="/publicSubmit")
	public ModelAndView publicafter(HttpServletRequest request, 
			ModelMap map
			)
			throws IOException {
		Content c=new Content();
		Jdbctool  jdl=new Jdbctool();
		Contentlist=jdl.getContentlist();
		request.setCharacterEncoding("UTF-8");
		String title=request.getParameter("title");
		String summary=request.getParameter("summary");
		String detail=request.getParameter("detail");
		String price=request.getParameter("price");
		
		int p1=Integer.parseInt(price);
		System.out.println(Contentlist.size());
		c.seticro(imagec);
		c.setimage("image1?id="+Contentlist.size());
		c.setprice(p1);
		c.settitle(title);
		c.setsummary(summary);
		c.setabstract1(summary);
		c.setdetail(detail);
		c.setid(Contentlist.size()+1);
		Contentlist.add(c);
		byte[] text=new byte[1024];
		text=detail.getBytes();
		c.settext(text);
		
		jdl.insert(c);		
		
		ModelAndView mv = new ModelAndView();
        mv.setViewName("publicSubmit");        
        mv.addObject("user",user);
        mv.addObject("product",c);
        return mv;
				}
	
	
	@RequestMapping(value="/delete")
	public void deletec(HttpServletRequest request, 
			HttpServletResponse response,
			ModelMap map
			)
			throws IOException {
		Imagetool imt=new Imagetool();//创建图片处理工具
		Jdbctool  jdl=new Jdbctool();
	
		String id=request.getParameter("id");
		int iid=Integer.parseInt(id);
		jdl.delete(iid);
		Contentlist=jdl.getContentlist();
	
		String json="";		
		json="{\"result\":\"true\",\"code\":\"200\"}";
		try 
		{
			response.getWriter().print(json);;
			response.getWriter().flush();;
			response.getWriter().close();;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request,
			ModelMap map
			)
			throws IOException {

		String id=request.getParameter("id");
		int	iid=Integer.parseInt(id)-1;
		
		Contentlist.get(iid).setbuyNum(0);//buynum进来设定都是0
		Contentlist.get(iid).setsummary(Contentlist.get(iid).getabstract1());//概述
		System.out.println(iid);
		byte[] b=new byte[1024];
		b=Contentlist.get(iid).gettext();
		String a=new String(b);//详细描述
		
		Contentlist.get(iid).setdetail(a);
	
		ModelAndView mv = new ModelAndView();
        mv.setViewName("edit");     
        mv.addObject("user",user);
        mv.addObject("product",Contentlist.get(iid));
        return mv;
	}
	

	
	@RequestMapping(value="/editSubmit")
	public ModelAndView editafter(HttpServletRequest request, 
			ModelMap map
			)
			throws IOException {

		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("id");
		int	iid=Integer.parseInt(id)-1;
		Content c=Contentlist.get(iid);
		String title=request.getParameter("title");
		String summary=request.getParameter("summary");
		String detail=request.getParameter("detail");
		String price=request.getParameter("price");
		
		System.out.println(title);
		int p1=Integer.parseInt(price);
		
		c.seticro(imagec);
		c.setimage("image1?id="+Contentlist.size());
		c.setprice(p1);
		c.settitle(title);
		c.setsummary(summary);
		c.setabstract1(summary);
		c.setdetail(detail);

		byte[] text=new byte[1024];
		text=detail.getBytes();
		c.settext(text);
		Jdbctool  jdl=new Jdbctool();//拿工具类
		
		jdl.edit(c);	
		Contentlist=jdl.getContentlist();//更新数据
		ModelAndView mv = new ModelAndView();
        mv.setViewName("editSubmit");        
        mv.addObject("user",user);
         mv.addObject("product",c);
        return mv;
				}
	

	public String getRequestPostStr(HttpServletRequest request) throws IOException{
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if(charEncoding == null){
		charEncoding = "UTF-8";
		}
		return new String(buffer,charEncoding);
		}

	public byte[] getRequestPostBytes(HttpServletRequest request) throws IOException{
		int contentLength = request.getContentLength();
		if(contentLength<0){
		return null;
		}
		byte[] buffer = new byte[contentLength];
		for(int i = 0;i<contentLength;){
		int readlen = request.getInputStream().read(buffer,i,contentLength - i);
		if(readlen == -1){
		break;
		}
		i += readlen;
		}
		return buffer;
		}
	

    public static byte[] readStream(InputStream inStream) throws Exception{     
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];     
        int len = -1;     
        while((len = inStream.read(buffer)) != -1){     
          outStream.write(buffer, 0, len);      
        }       
        outStream.close();      
        inStream.close();  
        return outStream.toByteArray();     
      } 
    
    

   
}
