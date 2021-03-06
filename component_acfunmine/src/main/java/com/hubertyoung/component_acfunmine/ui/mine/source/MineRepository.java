package com.hubertyoung.component_acfunmine.ui.mine.source;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.api.Api;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunmine.BuildConfig;
import com.hubertyoung.component_acfunmine.api.ApiHomeService;
import com.hubertyoung.environmentswitcher.EnvironmentSwitcher;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 16:45
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.mine.source
 */
public class MineRepository extends AbsRepository {
    public Flowable< User > requestUserInfo( String userId ) {
        HashMap map = new HashMap< String, String >();
        map.put( "userId", userId );
        return Api.getDefault( HostType.MY_RESULT )
                .getRetrofitClient()
                .setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
                .builder( ApiHomeService.class )
                .requestUserInfo( map )
                .compose( new DefaultTransformer() );
    }

    public Flowable< Boolean > requestCheckOfflineInfo() {
        return Api.getDefault( HostType.MY_RESULT )
                .getRetrofitClient()
                .setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
                .builder( ApiHomeService.class )
                .requestCheckOfflineInfo()
                .compose( new DefaultTransformer() );

    }

    public Flowable< Sign > requestPlatformLogin( String token, String openid, String platformName ) {
        Map< String, String > hashMap = new HashMap();
        hashMap.put( "clientId", Constants.cidKey );
        hashMap.put( "accessToken", token );
        hashMap.put( "openId", openid );
        hashMap.put( "type", platformName );
        return Api.getDefault( HostType.APP_HOST_ACCOUNT )
                .getRetrofitClient()
                .setBaseUrl( EnvironmentSwitcher.getAccountEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
                .builder( ApiHomeService.class )
                .requestPlatformLogin( hashMap )
                .compose( new DefaultTransformer<>() );

    }
}
