package com.romeo.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.romeo.domain.GeoLocation;
 
 
public class CAU {
 
	private static Properties props;

	public static final String GEOLOCATION_STREET="GEOLOCATION_STREET";
	public static final String GEOLOCATION_STREET_NUMBER="GEOLOCATION_STREET_NUMBER";
	public static final String GEOLOCATION_SUBLOCALITY1="GEOLOCATION_SUBLOCALITY1";
	public static final String GEOLOCATION_SUBLOCALITY2="GEOLOCATION_SUBLOCALITY2";
	public static final String GEOLOCATION_SUBLOCALITY3="GEOLOCATION_SUBLOCALITY3";
	public static final String GEOLOCATION_CITY="GEOLOCATION_CITY";
	public static final String GEOLOCATION_STATE="GEOLOCATION_STATE";
	public static final String GEOLOCATION_COUNTRY="GEOLOCATION_COUNTRY";
	public static final String GEOLOCATION_CONTINENT="GEOLOCATION_CONTINENT";
 
	
	public static Map<String, Object> map = new HashMap<String, Object>();
	public static HashMap<String, Set<String>> CONTINENT_COUNTRY = null;
	public static HashMap<String, String> COUNTRY_CONTINENT = null;

	/*static{
		props = new Properties();
		try {
			props.load(CAU.class.getClassLoader().getResourceAsStream("config-"+ System.getProperty("spring.profiles.active")+".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
*/
	/**
	 * Validates string if the string is null or empty
	 * 
	 * @param string
	 * @throws BaasException
	 */
	public static boolean isValidString(String string) {
		if (string == null || string.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if list is not null and not empty
	 * 
	 * @param list
	 * @return
	 * @throws BaasException
	 */
	public static <T> boolean isNonEmptyList(List<T> list) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns an 8 digit short uniqueid;
	 * 
	 * @return
	 */
	public static String getShortUUID() {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABSDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		Random r = new Random(System.currentTimeMillis());
		char[] id = new char[8];
		for (int i = 0; i < 8; i++) {
			id[i] = chars[r.nextInt(chars.length)];
		}
		return new String(id);
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	public static String getPropertyValue(String key){
		if(props!=null){
			return props.getProperty(key);
		}
		return null;
	}

	public static double getPropertyDoubleValue(String key){
		if(props!=null){
			if(props.getProperty(key)==null){
				//to know which key is missing from property file
				System.out.println(key);
			}
			else{
				return Double.parseDouble(props.getProperty(key));
			}
		}
		return 0;
	}

	   

	public static String ConvertMilliSecondsToFormattedDate(Date milliSeconds){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds.getTime());

		return formatter.format(calendar.getTime());
	}

	public static String removeSpecialCharacter(String str){
		str = str.replaceAll("[\\[\\]{}?_+.,!@#$%*();\\/|<>\"\'&^”][^a-zA-Z0-9]"," ").replaceAll("[\\[\\]{}?_+.,!@#$%*();\\/|<>’“\"\'&^]", " ").replaceAll("[\\s:`~?-]"," ").toLowerCase();
		return str;
	}

	public static Map<String, String> getQueryMap(String query)  
	{  
		String[] params = query.split("&");  
		Map<String, String> map = new HashMap<String, String>();  
		for (String param : params)  
		{  String [] p=param.split("=");
		String name = p[0];  
		if(p.length>1)  {String value = p[1];  
		map.put(name, value);
		}  
		}  
		return map;  
	} 
  
   
	/*public static void updateContinent(GeoLocation location) {
		location.setContinent(CAU.COUNTRY_CONTINENT.get(location.getCountry().toLowerCase()));
	}*/

  
	public static String constructedLocationAddress(GeoLocation location){
		StringBuilder sb = new StringBuilder("");
		@SuppressWarnings("serial")
		List<String> locPropMap = new ArrayList<String>(){{add(location.getStreetNumber());
		add(location.getStreet());
		add(location.getSubLocality3());
		add(location.getSubLocality2());
		add(location.getSubLocality1());
		add(location.getCity());
		add(location.getState());
		add(location.getCountry());}};
		if(!StringUtils.isEmpty(location.getName()) && locPropMap.indexOf(location.getName())<0)sb.append(location.getName()).append(" ");;
		if(!StringUtils.isEmpty(location.getStreet())){
			sb.append(location.getStreet()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getStreetNumber())){
			sb.append(location.getStreetNumber()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getSubLocality1())){
			sb.append(location.getSubLocality1()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getSubLocality2())){
			sb.append(location.getSubLocality2()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getSubLocality3())){
			sb.append(location.getSubLocality3()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getCity())){
			sb.append(location.getCity()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getState())){
			sb.append(location.getState()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getZipcode())){
			sb.append(location.getZipcode()).append(" ");
		}
		if(!StringUtils.isEmpty(location.getCountry())){
			sb.append(location.getCountry()).append(" ");
		}
		return sb.toString();
	}

	public static String encodeURIComponent(String s) {
		String result;

		try {
			result = URLEncoder.encode(s, "UTF-8")
					.replaceAll("\\+", "%20")
					.replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'")
					.replaceAll("\\%28", "(")
					.replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}

		return result;
	}

	public static String pushQuotes(String str){
		return "\""+str+"\"";
	}

	public static String readableName(String string) {
		return string.replaceAll("[^a-zA-Z0-9-_\\.]", "_").replace("\\", "-").replace("/", "-");
	}

	/**
	 * Use it to page over list items
	 * @param list
	 * @param page
	 * @param size
	 * @return
	 */
	public static <T> List<T> page(List<T> list,int page , int size){
		return list.stream()
				.skip(page * size)
				.limit(size)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	/**
	 * Use it to page over list items
	 * @param list
	 * @param page
	 * @param size
	 * @return
	 */
	public static <T> List<T> page(List<T> list,Pageable pageable){
		return page(list,pageable.getPageNumber(),pageable.getPageSize());
	}
	 
  
	public static String  getEnvironmentVariable(String varName){
		return System.getenv(varName);
	}	
     


	public static Object deepCloneSeriazableOnly(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
