package seri.core.io.test;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class CtrlEvalMap implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, String> ftlMap = new HashMap<String, String>();

    public void setProName(String proName) {
        this.ftlMap.put("proName",proName);
    }

    public void setProBegDate(String proBegDate) {
        this.ftlMap.put("proBegDate",proBegDate);
    }

    public void setProEndDate(String proEndDate) {
        this.ftlMap.put("proEndDate",proEndDate);
    }

    public void setDirector(String director) {
        this.ftlMap.put("director",director);
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

    public void setCtrlCompany(String ctrlCompany) {
        this.ftlMap.put("ctrlCompany",ctrlCompany);
    }

    public void setCtrlAddr(String ctrlAddr) {
        this.ftlMap.put("ctrlAddr",ctrlAddr);
    }

    public void setCtrlContact(String ctrlContact) {
        this.ftlMap.put("ctrlContact",ctrlContact);
    }

    public void setCtrlTel(String ctrlTel) {
        this.ftlMap.put("ctrlTel",ctrlTel);
    }

    public void setArrivingScore(String arrivingScore) {
        this.ftlMap.put("arrivingScore",arrivingScore);
    }

    public void setArrivingDesc(String arrivingDesc) {
        this.ftlMap.put("arrivingDesc",arrivingDesc);
    }

    public void setQualityScore(String qualityScore) {
        this.ftlMap.put("qualityScore",qualityScore);
    }

    public void setQualityDesc(String qualityDesc) {
        this.ftlMap.put("qualityDesc",qualityDesc);
    }

    public void setInvestmentScore(String investmentScore) {
        this.ftlMap.put("investmentScore",investmentScore);
    }

    public void setInvestmentDesc(String investmentDesc) {
        this.ftlMap.put("investmentDesc",investmentDesc);
    }

    public void setScheduleScore(String scheduleScore) {
        this.ftlMap.put("scheduleScore",scheduleScore);
    }

    public void setScheduleDesc(String scheduleDesc) {
        this.ftlMap.put("scheduleDesc",scheduleDesc);
    }

    public void setSecurityScore(String securityScore) {
        this.ftlMap.put("securityScore",securityScore);
    }

    public void setSecurityDesc(String securityDesc) {
        this.ftlMap.put("securityDesc",securityDesc);
    }

    public void setContractScore(String contractScore) {
        this.ftlMap.put("contractScore",contractScore);
    }

    public void setContractDesc(String contractDesc) {
        this.ftlMap.put("contractDesc",contractDesc);
    }

    public void setInformationScore(String informationScore) {
        this.ftlMap.put("informationScore",informationScore);
    }

    public void setInformationDesc(String informationDesc) {
        this.ftlMap.put("informationDesc",informationDesc);
    }

    public void setOrganizingScore(String organizingScore) {
        this.ftlMap.put("organizingScore",organizingScore);
    }

    public void setOrganizingDesc(String organizingDesc) {
        this.ftlMap.put("organizingDesc",organizingDesc);
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