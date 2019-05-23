package io.msa.apigateway.models;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;

public class Context {

	private String id;
	private int userId;
	private String userName;
	private String deviceId;
	private String deviceInfo;

	// Loc info
	private String ip;
	private String timeZone;
	private String city;
	private String cityid;
	private String regionName;
	private String countryCode;
	private String countryName;
	private String zipCode;
	private String latitude;
	private String longitude;
	// Weather info
	private String weatherMain;
	private String weatherDesc;
	private String windSpeed;
	private String snowQty;
	private String rainQty;
	private String mainTempMax;
	private String mainTempMin;
	private String mainTemp;
	private String cloudsAll;
	@CreatedDate
	@Transient
	private Date creationDt = new Date();

	public Context() {
	}

	public Context(String id, int userId, String userName, String deviceId, String deviceInfo, String ip,
			String timeZone, String city, String cityid, String regionName, String countryCode, String countryName,
			String zipCode, String latitude, String longitude, String weatherMain, String weatherDesc, String windSpeed,
			String snowQty, String rainQty, String mainTempMax, String mainTempMin, String mainTemp, String cloudsAll,
			Date creationDt) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.deviceId = deviceId;
		this.deviceInfo = deviceInfo;
		this.ip = ip;
		this.timeZone = timeZone;
		this.city = city;
		this.cityid = cityid;
		this.regionName = regionName;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.weatherMain = weatherMain;
		this.weatherDesc = weatherDesc;
		this.windSpeed = windSpeed;
		this.snowQty = snowQty;
		this.rainQty = rainQty;
		this.mainTempMax = mainTempMax;
		this.mainTempMin = mainTempMin;
		this.mainTemp = mainTemp;
		this.cloudsAll = cloudsAll;
		this.creationDt = creationDt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getWeatherMain() {
		return weatherMain;
	}

	public void setWeatherMain(String weatherMain) {
		this.weatherMain = weatherMain;
	}

	public String getWeatherDesc() {
		return weatherDesc;
	}

	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getSnowQty() {
		return snowQty;
	}

	public void setSnowQty(String snowQty) {
		this.snowQty = snowQty;
	}

	public String getRainQty() {
		return rainQty;
	}

	public void setRainQty(String rainQty) {
		this.rainQty = rainQty;
	}

	public String getMainTempMax() {
		return mainTempMax;
	}

	public void setMainTempMax(String mainTempMax) {
		this.mainTempMax = mainTempMax;
	}

	public String getMainTempMin() {
		return mainTempMin;
	}

	public void setMainTempMin(String mainTempMin) {
		this.mainTempMin = mainTempMin;
	}

	public String getMainTemp() {
		return mainTemp;
	}

	public void setMainTemp(String mainTemp) {
		this.mainTemp = mainTemp;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCloudsAll() {
		return cloudsAll;
	}

	public void setCloudsAll(String cloudsAll) {
		this.cloudsAll = cloudsAll;
	}

	public Date getCreationDt() {
		return creationDt;
	}

	public void setCreationDt(Date creationDt) {
		this.creationDt = creationDt;
	}
}
