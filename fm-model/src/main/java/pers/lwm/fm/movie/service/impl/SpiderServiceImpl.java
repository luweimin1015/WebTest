package pers.lwm.fm.movie.service.impl;

import org.springframework.stereotype.Service;
import pers.lwm.base.util.HttpUtil;
import pers.lwm.fm.movie.service.SpiderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lwm on 2016/3/26.
 */
@Service("spiderServiceImpl")
public class SpiderServiceImpl implements SpiderService {
    @Override
    public String getMv(int pageIndex) throws Exception {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMddHHm");
        String url = "http://service.channel.mtime.com/service/search.mcs?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Channel.Pages.SearchService&Ajax_CallBackMethod=SearchMovieByCategory&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2Fmovie%2Fsearch%2Fsection%2F%3Fnation%3D138%23pageIndex%3D"
                +pageIndex+
                "%26nation%3D138&t="+ sf.format(date)+(Math.random()*9+1)*1000000
+"&Ajax_CallBackArgument0=&Ajax_CallBackArgument1=0&Ajax_CallBackArgument2=138&Ajax_CallBackArgument3=0&Ajax_CallBackArgument4=0&Ajax_CallBackArgument5=0&Ajax_CallBackArgument6=0&Ajax_CallBackArgument7=0&Ajax_CallBackArgument8=&Ajax_CallBackArgument9=0&Ajax_CallBackArgument10=0&Ajax_CallBackArgument11=0&Ajax_CallBackArgument12=0&Ajax_CallBackArgument13=0&Ajax_CallBackArgument14=1&Ajax_CallBackArgument15=0&Ajax_CallBackArgument16=1&Ajax_CallBackArgument17=4&Ajax_CallBackArgument18="+pageIndex+"&Ajax_CallBackArgument19=0";
        System.out.println(url);
        //String result = HttpUtil.sendGet(url, null);
        String result = HttpUtil.getByHttpClient(url);
        return result;
    }


}
