package com.kx.studyview;

import com.kx.studyview.bean.BusLineInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2018/7/22.
 */
public interface Api {
   // @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("app/v5/ZkgdJh5OQNBrhK5kx2zT8ZmIIFSOQah*EBZn0tnovXpZKV+rxnQzaBkLFNdTWRWZ6GWSX2npugQRj4mqwSSFkM+11YpQlvHPQH0RoAXi8eW*U*NGe4Ovc9Ie5ay8VSWnzDWV4O+PPB1Jr4ZzON+c8YB83U4G7+CQCvHPsUvug8NYqiQD+BNj0hVKT*nmB9s7ADFKw*BBbxHKsHX3NkFEeRffTAcVuksM2M8QX+j1Jc6RiWjeUU*RLAFTUCASE*kg6fkKxyD6l9QYTR1VuZ7y3ZXo7JpQrmCYt6EFw*lxe0HNW4+sbmiTn3kSvhv9wCngnyYVI6ezLNeZM1xlw9LaFA==")
    Call<BusLineInfo> getBusLineInfo();
  //  @GET("app/v5/q5RC3rGyTe34n6Pw+7zlTlFEcX2ZgTWpN*JHVS1KSWCwQqLwgg3xSg4284irzDRGUPhjQE4iL5oanvZHzT6U6Mb0J3PtDmAwumX*yxkjQcqW5QTy8urbrc7LGmp8feRgL4Iykr0hL0iQddhfpNldAij4tV1OfDzCouv8JonI+79n0s3EKXhqFH8tvr0WZQrsOEKcawQofEw6NzuNw31Iq6a03uxRYtmqlaH4GfWBEfT9odBp6AOnJCCxQnU+nM10pzDLQQsSVPTxVclDfFqUNq5ugtB2xcMDWq*VC1qL0FlY1eYS2fBJP5hb47ehc7g1QfYudAc*AUbKExAGjlyDfA==")
  //  Call<BusInfo> getBusInfo();

}
