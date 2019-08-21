package com.cmcc.identification.web;

import com.cmcc.identification.cache.GclibCacheManager;
import com.cmcc.identification.entity.CharacteristicsLibrary;
import com.cmcc.identification.vo.CharacteristicsLibraryVo;
import com.cmcc.identification.vo.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gclib-service")
public class GclibWeb {
	
	@Autowired
	private GclibCacheManager gclibCacheManager;

    //特征库入库
    @PostMapping("characteristicsLibrary")
    public R addCharacteristicsLibrary(@RequestBody CharacteristicsLibrary characteristicsLibrary) {
//        R result = null;
//        try {
//            result = R.OK("成功上报");
//        } catch (Exception e) {
//            result = R.ERROR(500, e.toString());
//        }
//        return result;
        return gclibCacheManager.featureStorage(characteristicsLibrary);
    }

    //特征库删除
    @DeleteMapping("characteristicsLibrary")
    public R deletedCharacteristicsLibrary(@RequestBody CharacteristicsLibraryVo characteristicsLibraryVo) {
//        R result = null;
//        try {
//            List<String> list = new ArrayList<String>();
//            for(String uuid:characteristicsLibraryVo.getUuid()){
//                list.add(uuid);
//            }
//            result = R.OK(list);
//        } catch (Exception e) {
//            result = R.ERROR(500, e.toString());
//        }
//        return result;
        return gclibCacheManager.featureDelete(characteristicsLibraryVo);
    }
}
