//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.payment.service.openfeign;

import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransParam;
import cn.edu.xmu.oomall.payment.service.openfeign.param.PostTransRetObj;
import org.springframework.stereotype.Service;

@Service
public class WePayService {

    public PostTransRetObj postTransaction(PostTransParam param){
        return null;
    }
}
