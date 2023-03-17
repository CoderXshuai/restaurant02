package youyanzu.seu.restaurant02.contorller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import youyanzu.seu.restaurant02.Enums.ResultEnum;
import youyanzu.seu.restaurant02.Utils.ResultUtil;
import youyanzu.seu.restaurant02.entity.Member;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.service.IMemberCategoryService;
import youyanzu.seu.restaurant02.service.IMemberService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberCategoryService memberCategoryService;

    @RequestMapping(value = "/memberList", method = RequestMethod.GET)
    public Result memberList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                             @RequestParam(value = "memberCode", required = false) String memberCode, @RequestParam(value = "mName", required = false) String mName,
                             @RequestParam(value = "mGender", required = false) String mGender, @RequestParam(value = "mBirthday", required = false) String mBirthday,
                             @RequestParam(value = "mcName", required = false) String mcName) {
        //创建member对象
        Member member = new Member();
        if (memberCode != null)
            member.setMemberCode(memberCode);
        if (mName != null)
            member.setMName(mName);
        if (mGender != null)
            member.setMGender(mGender);
        if (mBirthday != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            member.setMBirthday(LocalDate.parse(mBirthday, fmt));
        }
        member.setMcId(mcName == null ? null : memberCategoryService.getIdByName(mcName));
        //调用service进行查询}
        Page<Member> list = memberService.getList(pageNum, pageSize, member);
        List<Member> memberList = list.getRecords();
        //封装结果
        HashMap<String, Object> map = new HashMap<>();
        map.put("memberList", memberList);
        return ResultUtil.success(map, list.getTotal());
    }

    //    @RequestParam(value = "phone") String memberCode, @RequestParam(value = "mName") String name,
//    @RequestParam(value = "mGender") String gender, @RequestParam(value = "mBirthday"
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public Result<String> addMember(@RequestBody Map<String, Object> map) {
        //创建member对象


        Member member = new Member();
        member.setMemberCode(map.get("phone").toString());
        member.setMPhone(map.get("phone").toString());
        if (map.containsKey("name"))
            member.setMName(map.get("name").toString());
        if (map.containsKey("gender"))
            member.setMGender(map.get("gender").toString());
        if (map.containsKey("birthday")) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            member.setMBirthday(LocalDate.parse(map.get("birthday").toString(), fmt));
        }
        member.setMcId(1);
        //判断是否存在
        if (memberService.memberExist(member.getMemberCode())) {
            return ResultUtil.error(ResultEnum.USER_IS_EXIST);
        } else {//判断是否添加成功
            if (memberService.addMember(member)) {
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        }
    }

    //    @RequestParam(value = "mName") String name, @RequestParam(value = "mGender") String gender,
//    @RequestParam(value = "mBirthday")String birthday,@RequestParam(value = "memberCode") String loginCode
    @RequestMapping(value = "/editMember", method = RequestMethod.POST)
    public Result editMember(@RequestBody Map<String, Object> map) {

        //创建member对象
        Member member = new Member();
        if (map.containsKey("name"))
            member.setMName(map.get("name").toString());
        if (map.containsKey("gender"))
            member.setMGender(map.get("gender").toString());
        if (map.containsKey("birthday")) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            member.setMBirthday(LocalDate.parse(map.get("birthday").toString(), fmt));
        }
        //判断是否修改成功
        if (memberService.editMember(map.get("loginCode").toString(), member)) {
            return ResultUtil.success();
        } else return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    //    @RequestParam(value = "memberCode") String loginCode
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public Result deleteMember(@RequestBody Map<String, Object> map) {
        if (memberService.deleteMemberById(map.get("memberCode").toString())) {
            return ResultUtil.success();
        } else return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "getMemberByMemberCode", method = RequestMethod.GET)
    public Result<Member> findMemberByMemberCode(@RequestParam(value = "memberCode") String memberCode) {
        Result<Member> result = new Result<>();
        Member member = memberService.getMemberByMemberCode(memberCode);
        if (member != null) {
            return ResultUtil.success(member);
        } else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

}
