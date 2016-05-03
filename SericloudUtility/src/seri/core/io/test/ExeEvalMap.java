package seri.core.io.test;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class ExeEvalMap implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, String> ftlMap = new HashMap<String, String>();

    public void setProName(String proName) {
        this.ftlMap.put("proName",proName);
    }

    public void setProAddr(String proAddr) {
        this.ftlMap.put("proAddr",proAddr);
    }

    public void setProBegDate(String proBegDate) {
        this.ftlMap.put("proBegDate",proBegDate);
    }

    public void setProEndDate(String proEndDate) {
        this.ftlMap.put("proEndDate",proEndDate);
    }

    public void setManager(String manager) {
        this.ftlMap.put("manager",manager);
    }

    public void setManagerTel(String managerTel) {
        this.ftlMap.put("managerTel",managerTel);
    }

    public void setContractNum(String contractNum) {
        this.ftlMap.put("contractNum",contractNum);
    }

    public void setContractCost(String contractCost) {
        this.ftlMap.put("contractCost",contractCost);
    }

    public void setConstructionUnit(String constructionUnit) {
        this.ftlMap.put("constructionUnit",constructionUnit);
    }

    public void setConstructionAddr(String constructionAddr) {
        this.ftlMap.put("constructionAddr",constructionAddr);
    }

    public void setConsContact(String consContact) {
        this.ftlMap.put("consContact",consContact);
    }

    public void setConsTel(String consTel) {
        this.ftlMap.put("consTel",consTel);
    }

    public void setExecutionCompany(String executionCompany) {
        this.ftlMap.put("executionCompany",executionCompany);
    }

    public void setExeAddr(String exeAddr) {
        this.ftlMap.put("exeAddr",exeAddr);
    }

    public void setExeContact(String exeContact) {
        this.ftlMap.put("exeContact",exeContact);
    }

    public void setExeTel(String exeTel) {
        this.ftlMap.put("exeTel",exeTel);
    }

    public void setArrivingScore(String arrivingScore) {
        this.ftlMap.put("arrivingScore",arrivingScore);
    }

    public void setArrivingDesc(String arrivingDesc) {
        this.ftlMap.put("arrivingDesc",arrivingDesc);
    }

    public void setScheduleScore(String scheduleScore) {
        this.ftlMap.put("scheduleScore",scheduleScore);
    }

    public void setScheduleDesc(String scheduleDesc) {
        this.ftlMap.put("scheduleDesc",scheduleDesc);
    }

    public void setQualityScore(String qualityScore) {
        this.ftlMap.put("qualityScore",qualityScore);
    }

    public void setQualityDesc(String qualityDesc) {
        this.ftlMap.put("qualityDesc",qualityDesc);
    }

    public void setSecurityScore(String securityScore) {
        this.ftlMap.put("securityScore",securityScore);
    }

    public void setSecurityDesc(String securityDesc) {
        this.ftlMap.put("securityDesc",securityDesc);
    }

    public void setSalaryScore(String salaryScore) {
        this.ftlMap.put("salaryScore",salaryScore);
    }

    public void setSalaryDesc(String salaryDesc) {
        this.ftlMap.put("salaryDesc",salaryDesc);
    }

    public void setPerformanceScore(String performanceScore) {
        this.ftlMap.put("performanceScore",performanceScore);
    }

    public void setPerformanceDesc(String performanceDesc) {
        this.ftlMap.put("performanceDesc",performanceDesc);
    }

    public Map<String, String> getMap() {
        return ftlMap;
    }

}