package com.zskjprojectj.andoubusinessside.http;


import com.zskjprojectj.andoubusinessside.model.ADProvince;
import com.zskjprojectj.andoubusinessside.model.Item;
import com.zskjprojectj.andoubusinessside.model.Order;
import com.zskjprojectj.andoubusinessside.model.OrderDetail;
import com.zskjprojectj.andoubusinessside.model.Shop;
import com.zskjprojectj.andoubusinessside.model.User;
import com.zskjprojectj.andoubusinessside.model.UserT;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    Observable<BaseResult<OrderDetail>> orderDetail(@Field("uid") String uid,
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


}
