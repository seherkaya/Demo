package com.metasoft.rpiDemo.model;

public class ApiResponse {

    private Boolean successful;
    private Integer messageCode;
    private String messageText;

    private String requestedApi;
    private Long requestTime;
    private Long responseTime;

    private String returnDataType;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(String requestedApi) {
        this.requestTime=System.currentTimeMillis();
        this.requestedApi=requestedApi;
    }

    public ApiResponse step1(String requestedApi){
        this.requestTime=System.currentTimeMillis();
        this.requestedApi=requestedApi;
        return this;
    }

    public ApiResponse step2(Boolean successful, Integer messageCode, String messageText, String returnDataType, Object data){
        this.successful = successful;
        this.messageCode = messageCode;
        this.messageText = messageText;
        this.returnDataType = returnDataType;
        this.data = data;
        this.responseTime = System.currentTimeMillis();
        return this;
    }

    //region GETTER-SETTER
    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Integer getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Integer messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getRequestedApi() {
        return requestedApi;
    }

    public void setRequestedApi(String requestedApi) {
        this.requestedApi = requestedApi;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public String getReturnDataType() {
        return returnDataType;
    }

    public void setReturnDataType(String returnDataType) {
        this.returnDataType = returnDataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //endregion

    @Override
    public String toString() {
        return "ApiResponse{" +
                "successful=" + successful +
                ", messageCode=" + messageCode +
                ", messageText='" + messageText + '\'' +
                ", requestedApi='" + requestedApi + '\'' +
                ", requestTime=" + requestTime +
                ", responseTime=" + responseTime +
                ", returnDataType='" + returnDataType + '\'' +
                ", data=" + data +
                '}';
    }

    public ApiResponse _500(){
        return this.step2(false,500,null,null,null);
    }

    public ApiResponse _404(){
        return this.step2(false,404,null,null,null);
    }

    public ApiResponse _200(Object object){
        return this.step2(true,200,null,null,object);
    }

}
