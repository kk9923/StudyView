package com.kx.studyview.bean;

import java.util.List;

/**
 * Created by admin on 2018/7/22.
 */
public class BusInfo {

    /**
     * resultCode : 1
     * resultDes :
     * data : ["51742|12|3|0|114.53283250019052|30.48868771773341","5785|12|11|0|114.48083351183516|30.501703568702247","51050|12|16|0|114.44859884854635|30.510011151212208","51738|12|19|0|114.43123457813036|30.505956261127352"]
     */

    private String resultCode;
    private String resultDes;
    private List<String> data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDes() {
        return resultDes;
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
