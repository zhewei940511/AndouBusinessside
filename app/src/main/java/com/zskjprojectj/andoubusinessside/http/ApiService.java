package com.zskjprojectj.andoubusinessside.http;


import com.zhuosongkj.android.library.model.BaseResult;
import com.zhuosongkj.android.library.model.ListData;
import com.zskjprojectj.andoubusinessside.model.ADProvince;
import com.zskjprojectj.andoubusinessside.model.DealDetail;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.Review;
import com.zskjprojectj.andoubusinessside.model.Shop;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.model.UserT;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @POST("api/login/login_p")
    @FormUrlEncoded
    Observable<BaseResult<UserT>> login(@Field("phone") String phone, @Field("password") String password);

    @POST("api/login/send")
    @FormUrlEncoded
    Observable<BaseResult<Object>> getCode(@Field("phone") String phone,
                                           @Field("type") String type);

    @POST("api/login/forget")
    @FormUrlEncoded
    Observable<BaseResult<Object>> resetPassword(@Field("phone") String phone,
                                                 @Field("verify") String code,
                                                 @Field("new_password") String new_password);

    @POST("api/goods/merchants")
    @FormUrlEncoded
    Observable<BaseResult<List<User.Role>>> merchantsInfo(@Field("uid") String uid);

    @POST("api/common/district")
    Observable<BaseResult<List<ADProvince>>> districts();

    @POST("api/merchant/information")
    @FormUrlEncoded
    Observable<BaseResult<Object>> uploadMerchantsInfo(@Field("uid") String uid,
                                                       @Field("type_id") int type_id,
                                                       @Field("name") String name,
                                                       @Field("user_name") String user_name,
                                                       @Field("tel") String tel,
                                                       @Field("province_id") int province_id,
                                                       @Field("city_id") int city_id,
                                                       @Field("area_id") int area_id,
                                                       @Field("address") String address,
                                                       @Field("desc") String desc,
                                                       @Field("banner_img") String bannerImg,
                                                       @Field("logo_img") String logo_img,
                                                       @Field("management_img") String management_img);

    @POST("api/goods/centre")
    @FormUrlEncoded
    Observable<BaseResult<RoleInfoResponse>> roleInfo(@Field("uid") String uid,
                                                      @Field("id") String id,
                                                      @Field("merchant_type_id") int merchant_type_id);

    @POST("api/goods/lists")
    @FormUrlEncoded
    Observable<BaseResult<ListData<Order>>> orderList(@Field("uid") String uid,
                                                      @Field("id") String id,
                                                      @Field("merchant_type_id") int merchant_type_id,
                                                      @Field("type") Integer type,
                                                      @Field("page") int page);

    @POST("api/goods/ordersDetails")
    @FormUrlEncoded
    Observable<BaseResult<Order>> orderDetail(@Field("uid") String uid,
                                              @Field("id") String id,
                                              @Field("merchant_type_id") int merchant_type_id,
                                              @Field("order_sn") String order_id);

    @POST("api/goods/manage")
    @FormUrlEncoded
    Observable<BaseResult<ListData<Item>>> itemList(@Field("uid") String uid,
                                                    @Field("id") String id,
                                                    @Field("merchant_type_id") int merchant_type_id,
                                                    @Field("page") int page);

    @POST("api/goods/store")
    @FormUrlEncoded
    Observable<BaseResult<Shop>> shopInfo(@Field("uid") String uid,
                                          @Field("id") String id,
                                          @Field("merchant_type_id") int merchant_type_id);

    @POST("api/goods/saveStore")
    @FormUrlEncoded
    Observable<BaseResult<Object>> saveShopInfo(@Field("uid") String uid,
                                                @Field("id") String id,
                                                @Field("merchant_type_id") int merchant_type_id,
                                                @Field("name") String name,
                                                @Field("contact") String contact,
                                                @Field("mobile") String mobile,
                                                @Field("desc") String desc,
                                                @Field("address") String address,
                                                @Field("banner_img") String banner_img,
                                                @Field("nickname") String nickname,
                                                @Field("tel") String tel,
                                                @Field("return_address") String return_address);

    @POST("api/goods/awaitUpdate")
    @FormUrlEncoded
    Observable<BaseResult<Object>> editPrice(@Field("uid") String uid,
                                             @Field("id") String id,
                                             @Field("order_sn") String order_id,
                                             @Field("order_money") double pay_money);

    @POST("api/goods/classify")
    @FormUrlEncoded
    Observable<BaseResult<Object>> newCategory(@Field("uid") String uid,
                                               @Field("id") String id,
                                               @Field("merchant_type_id") int merchant_type_id,
                                               @Field("name") String name);

    @POST("api/goods/manageDel")
    @FormUrlEncoded
    Observable<BaseResult<Object>> delItem(@Field("uid") String uid,
                                           @Field("id") String id);

    @POST("api/goods/affirm")
    @FormUrlEncoded
    Observable<BaseResult<Object>> refund(@Field("uid") String uid,
                                          @Field("id") String id);

    @POST("api/goods/putaway")
    @FormUrlEncoded
    Observable<BaseResult<Object>> goodsOn(@Field("uid") String uid,
                                           @Field("id") String id);

    @POST("api/goods/soldOut")
    @FormUrlEncoded
    Observable<BaseResult<Object>> goodsOff(@Field("uid") String uid,
                                            @Field("id") String id);

    @POST("api/goods/water")
    @FormUrlEncoded
    Observable<BaseResult<ListData<DealDetail>>> balanceDetail(@Field("uid") String uid,
                                                               @Field("id") String id,
                                                               @Field("merchant_type_id") int merchant_type_id,
                                                               @Field("status") int status,
                                                               @Field("page") int page);

    @POST("api/goods/quit")
    @FormUrlEncoded
    Observable<BaseResult<Object>> logout(@Field("uid") String uid);

    @POST("api/common/express")
    @FormUrlEncoded
    Observable<BaseResult<List<Express>>> express(@Field("uid") String uid);

    @POST("api/goods/uploads")
    @Multipart
    Observable<BaseResult<String>> uploadImg(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);

    @POST("api/goods/observer")
    @FormUrlEncoded
    Observable<BaseResult<Review>> reviewDetail(@Field("uid") String uid,
                                                @Field("merchant_type_id") String merchant_type_id,
                                                @Field("order_id") String order_id,
                                                @Field("type") String type);

    @POST("api/goods/delete")
    @FormUrlEncoded
    Observable<BaseResult<Object>> delReview(@Field("uid") String uid,
                                             @Field("merchant_type_id") String merchant_type_id,
                                             @Field("id") String id);


}
