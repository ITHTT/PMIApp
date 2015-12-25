package com.tysci.pmi.activitys;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;
import com.tysci.applibrary.base.BaseActivity;
import com.tysci.applibrary.models.EventObject;
import com.tysci.applibrary.networks.HttpClientUtil;
import com.tysci.applibrary.utils.CookieUtil;
import com.tysci.pmi.R;
import com.tysci.pmi.networks.HttpUrls;
import com.tysci.pmi.utils.ToastUtil;
import com.tysci.pmi.utils.UserInfoUtil;

import org.simple.eventbus.Subscriber;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/25.
 */
public class LoginActivity extends BaseActivity{
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.et_user_account)
    protected EditText etUserAccount;
    @Bind(R.id.et_user_password)
    protected EditText etUserPassword;

    private static final String EVENT_USER_LOGIN="event_user_login";
    private static final String EVENT_GET_TOKEN="evnet_get_token";

    @Override
    protected void setRootContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("登录");

    }

    @Override
    protected void loadDatas() {

    }

    @OnClick(R.id.bt_login)
    protected void login(View view){
        final String account=etUserAccount.getText().toString();
        final String password=etUserPassword.getText().toString();

        if(TextUtils.isEmpty(account)){
            ToastUtil.toastMsg(this, "请输入用户名");
            return;
        }

        if(TextUtils.isEmpty(password)){
            ToastUtil.toastMsg(this,"请输入密码");
            return;
        }
        requestLogin(EVENT_USER_LOGIN,account,password);
    }

    /**
     * 用户登录
     * @param eventTag
     * @param account
     * @param password
     */
    private void requestLogin(String eventTag,String account,String password){
        Map<String,String> params=new HashMap<String,String>(2);
        params.put("email",account);
        params.put("password",password);
        HttpClientUtil.getHttpClientUtil().sendPostRequest(Tag, eventTag, HttpUrls.USER_LOGIN_URL, params);
    }

    /**
     * 获取Token
     * @param evnetTag
     * @param cookie
     */
    private void getToken(String evnetTag,String cookie){
        Map<String,String>headers=new HashMap<>(1);
        headers.put("cookie",cookie);
        HttpClientUtil.getHttpClientUtil().sendGetRequest(Tag, evnetTag, HttpUrls.USER_GET_TOKEN, 30 * 60, headers, null);
    }

    private void connectRongYun(String token){

    }

    @Subscriber(tag=EVENT_USER_LOGIN)
    private void onUserLoginResponse(EventObject eventObject){
        if(eventObject!=null){
            int responseCode=eventObject.getResponseCode();
            if(responseCode==EventObject.RESPONSE_CODE_OK){
                String result= (String) eventObject.getDatas();
                KLog.e("result:" + result);
                if(!TextUtils.isEmpty(result)){
                    JSONObject object=JSONObject.parseObject(result);
                    if(object!=null&&!object.isEmpty()){
                       int code= object.getIntValue("code");
                        if(code==200){
                            JSONObject resultObject=object.getJSONObject("result");
                            String id=resultObject.getString("id");
                            String userName=resultObject.getString("username");
                            String cookie= CookieUtil.getCookieUtil().getCookie();
                            if(!TextUtils.isEmpty(cookie)){
                                getToken(EVENT_GET_TOKEN,cookie);
                            }
                        }
                    }
                }
            }
        }
    }

    @Subscriber(tag=EVENT_GET_TOKEN)
    private void onUserGetTokenResponse(EventObject eventObject){
        if(eventObject!=null){
            int responseCode=eventObject.getResponseCode();
            if(responseCode==EventObject.RESPONSE_CODE_OK){
                String result=(String)eventObject.getDatas();
                if(!TextUtils.isEmpty(result)){
                    KLog.e("获取的Token:"+result);
                    JSONObject object=JSONObject.parseObject(result);
                    if(object!=null&&!object.isEmpty()){
                        int code=object.getIntValue("code");
                        if(code==200){
                            JSONObject resultObject=object.getJSONObject("result");
                            if(resultObject!=null&&!resultObject.isEmpty()){
                                String token=resultObject.getString("token");
                                if(!TextUtils.isEmpty(token)){
                                    UserInfoUtil.setUserToken(this,token);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
