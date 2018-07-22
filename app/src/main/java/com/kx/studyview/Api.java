package com.kx.studyview;

import com.kx.studyview.bean.BusInfo;
import com.kx.studyview.bean.BusLineInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2018/7/22.
 */
public interface Api {
   // @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("app/v5/CaU1L51+qK3hEf+57BCfbTbdDWrnbIa3iU8utK6iFECH3GhQbAmOXsCJ+rF5Adtf9AXa9uZriuVmcMURM6aLBXUuwflUukZEuwfBBIWgmhHI39yMFEKEo1T8zAv2M6QvGwKcYzdDkxgmMXCe*NarBkhc5rZRr96o27bFEeyut1k6ZYphpVE5NJn62LlYJ2ZMOUpq2mCMvuh*UYsdPORba4mPj3k9t+g6mD0d*K6NVTid55MJ49pemxBngSfaj38ECVWZtj85dG2UvDZQ1SonmCR1BczEW2t6NAJHH0BwdkesZEjkIo+YWHTaL1o4tfFmn2B0E9jfaPcnMQpBPuoiTQ==")
    Call<BusLineInfo> getBusLineInfo();
    @GET("app/v5/q5RC3rGyTe34n6Pw+7zlTlFEcX2ZgTWpN*JHVS1KSWCwQqLwgg3xSg4284irzDRGUPhjQE4iL5oanvZHzT6U6Mb0J3PtDmAwumX*yxkjQcqW5QTy8urbrc7LGmp8feRgL4Iykr0hL0iQddhfpNldAij4tV1OfDzCouv8JonI+79n0s3EKXhqFH8tvr0WZQrsOEKcawQofEw6NzuNw31Iq6a03uxRYtmqlaH4GfWBEfT9odBp6AOnJCCxQnU+nM10pzDLQQsSVPTxVclDfFqUNq5ugtB2xcMDWq*VC1qL0FlY1eYS2fBJP5hb47ehc7g1QfYudAc*AUbKExAGjlyDfA==")
    Call<BusInfo> getBusInfo();

}
