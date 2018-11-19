package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.queue.BlockQueue;


import sun.misc.BASE64Decoder;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

/*
	@Test
	public void contextLoads() {
	}
*/

	@Autowired
	private Producer producer;

	@Autowired
    private BlockQueue blockQueue;

	@Autowired
	private JavaMailSender javaMailSender;
	@Test
	public void contextLoads() throws InterruptedException {
		Destination destination = new ActiveMQQueue("mytest.queue");

		//for(int i=0; i<100; i++){
			producer.sendMessage(destination, "myname is chhliu!!!");
		//}
	}

	@Test
    public void blockQueueTest(){
	    for(int i=0;i<100;i++){
	        blockQueue.push(new Long(i));
//            System.out.println(i);
        }
    }


    @Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1562265312@qq.com");
		message.setTo("1562265312@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		javaMailSender.send(message);
	}

	@Test
	public void json(){
		String jsonStr="{    \"time_used\":1448,    \"verification\":{       \"idcard\":{          \"confidence\":86.63057,          \"thresholds\":{             \"1e-3\":62.168713,             \"1e-5\":74.39926,             \"1e-4\":69.31534,             \"1e-6\":78.038055          }       }    },    \"request_id\":\"1531397565,39b19451-393c-4fc4-8fae-6dc74b2b00d7\",    \"images\":{       \"image_best\":\"xxxxxxxxxxx\"    },    \"biz_no\":\"\",    \"result_message\":\"SUCCESS\",    \"result_code\":1000 }";
		JSONObject jsonObject=JSONObject.parseObject(jsonStr);
		JSONObject jsonObject1=jsonObject.getJSONObject("verification");
		System.out.println("jsonObject1:"+jsonObject1.getJSONObject("idcard").get("confidence"));
	}

	/*public static void main(String[] args) {
		//模拟获取到的json对象数组格式的字符串{"total":"1","rows":[{"createdate":"2017-10-26 00:00:00","name":"aaaa"},{"createdate":"2017-10-27 13:39:35","name":"bbb"}]}
		String jsonArrayStr="{\"total\":\"1\",\"rows\":[{\"createdate\":\"2017-10-26 00:00:00\",\"name\":\"aaaa\"},{\"createdate\":\"2017-10-27 13:39:35\",\"name\":\"bbb\"}]}";
		System.out.println("1---将符合json对象数组格式的字符串jsonArrayStr====="+jsonArrayStr);

		//将符合json对象数组格式的字符串转换为json对象
		JSONObject jsonObject=JSONObject.parseObject(jsonArrayStr);
		System.out.println("2---转换为JSONObject====="+jsonObject);

		//取出json对象里的total数据。
		String strTotal=jsonObject.getString("total");
		System.out.println("3---取出json对象里的total数据  total====="+strTotal);

		//取出json对象里的rows数据
		String strRows=jsonObject.getString("rows");
		System.out.println("4---取出json对象里的rows数据  jsonRows====="+strRows);

		//转换成json对象数组
		JSONArray jsonArrayRows= JSONArray.parseArray(strRows);
		//从数组里取出各值。
		for(int i=0;i<jsonArrayRows.size();i++) {
			JSONObject jsonObjectRows=(JSONObject)jsonArrayRows.get(i);
			String createdate=jsonObjectRows.get("createdate").toString();
			String name=jsonObjectRows.get("name").toString();
			System.out.println("5---createdate====="+createdate+"  |   name====="+name);
		}
	}*/


	public static void uploadFile(String fileName){
		int i;
		File file=new File(fileName);
		File newFile=new File("D:/img/image1.jpg");
		FileOutputStream out=null;
		FileInputStream input=null;
		try{
			out=new FileOutputStream(newFile);
			input=new FileInputStream(file);
		    byte[] buf=new byte[1024];
		    int len=0;
            while((len=input.read(buf))!=-1){
            out.write(buf,0,len);
		    }
		input.close();
        out.close();

		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				if(input!=null){
					input.close();
				}
			}catch (IOException e){
				throw new RuntimeException("关闭输入流失败");
			}

			try{
				if(out!=null){
					out.close();
				}
			}catch (IOException e){
				throw new RuntimeException("关闭输出流失败");
			}
		}
		System.out.println("图片保存成功！");
	}


	/*public void uploadImg(){
		String newLine = "\r\n";
		String boundaryPrefix = "--";
		// data division
		String BOUNDARY = UUID.randomUUID().toString();

		String target = ""; // 要提交的目标地址
		URL url;
		try {
			url = new URL(target);
			HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection(); // 创建一个HTTP连接
			urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
			urlConn.setDoInput(true); // 向连接中写入数据
			urlConn.setDoOutput(true); // 从连接中读取数据
			urlConn.setUseCaches(false); // 禁止缓存
			urlConn.setInstanceFollowRedirects(true); // 自动执行HTTP重定向
			urlConn.setRequestProperty("connection", "Keep-Alive");
			urlConn.setRequestProperty("Charset", "UTF-8");
			urlConn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY); // 设置内容类型
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream()); // 获取输出流

			// 上传文件
			File file = new File("");
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);

			*//**
			 *文件参数,photo参数名可以随意修改
			 *photo 为服务器的key
			 *如果服务器设置了这个key就要改成响应的参数
			 *//*
			sb.append("Content-Disposition: form-data;name=\"photo\";filename=\""
					+ file.getName() + "\"" + newLine);
			sb.append("Content-Type:application/octet-stream");
			// 参数头设置完以后需要两个换行，然后才是参数内容
			sb.append(newLine);
			sb.append(newLine);
			// 将参数头的数据写入到输出流中
			out.write(sb.toString().getBytes());
			// 数据输入流,用于读取文件数据
			DataInputStream in = new DataInputStream(
					new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			// 每次读1KB数据,并且将文件数据写入到输出流中
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			// 最后添加换行
			out.write(newLine.getBytes());
			in.close();

			// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
					+ boundaryPrefix + newLine).getBytes();
			// 写上结尾标识
			out.write(end_data);
			out.flush(); // 输出缓存
			out.close(); // 关闭数据输出流
			*//*Log.i(TAG, "getResponseCode:" + urlConn.getResponseCode());
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) { // 判断是否响应成功
				InputStreamReader in1 = new InputStreamReader(
						urlConn.getInputStream(), "utf-8"); // 获得读取的内容，utf-8获取内容的编码
				BufferedReader buffer = new BufferedReader(in1); // 获取输入流对象
				String inputLine = null;
				Log.d(TAG, "inputLine:" + buffer.readLine());
				while ((inputLine = buffer.readLine()) != null) {
					Log.d(TAG, inputLine + "\n");
					try {
						JSONObject reader = new JSONObject(inputLine);// 使用JSONObject解析
						JSONObject reObjectData = reader
								.getJSONObject("data");

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i(TAG, e.getMessage());
					}
				}

				in1.close(); // 关闭字符输入流
			}
			urlConn.disconnect(); // 断开连接
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.i(TAG, e.getMessage());
	}*/
	@Test
	public void img(){
		uploadFile("D:/img/image.jpg");
	}

	@Test
	public void test2(){
		String name="消费-上海-三亚";
		String productName=name.substring(name.indexOf("-")+1);
		System.out.println(productName);
	}

	//base64转换图片
	@Test
	public void image(){
		final String imageBase64="";
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			//base64解码
			byte[] bytes=decoder.decodeBuffer(imageBase64);
			for(int i=0;i<bytes.length;++i){
				if(bytes[i]<0){
					bytes[i]+=256;
				}
			}

			OutputStream out=new FileOutputStream("D:/img/base.jpg");
			out.write(bytes);
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
