package youyanzu.seu.restaurant02.contorller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import youyanzu.seu.restaurant02.Enums.ResultEnum;
import youyanzu.seu.restaurant02.Utils.ResultUtil;
import youyanzu.seu.restaurant02.entity.Member;
import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.service.IMemberService;
import youyanzu.seu.restaurant02.service.IMemberCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
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
    public Result memberList(@RequestParam("page_num") int pageNum, @RequestParam("page_size")int pageSize,
                                           @RequestParam(value = "member_code", required = false) String memberCode, @RequestParam(value = "m_name", required = false) String mName,
                                           @RequestParam(value ="m_gender", required = false) String mGender, @RequestParam(value ="m_birthday", required = false) String mBirthday,
                                           @RequestParam(value ="mc_name", required = false) String mcName){
        //创建member对象
        Member member = new Member();
        member.setMemberCode(memberCode);
        member.setMName(mName);
        member.setMGender(mGender);
        System.out.println(mGender);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        member.setMBirthday(LocalDate.parse(mBirthday, fmt));
        member.setMcId(mcName==null?null:memberCategoryService.getIdByName(mcName));
        //调用service进行查询
        Page<Member> list = memberService.getList(pageNum, pageSize, member);
        List<Member> records = list.getRecords();
        //封装结果
        return ResultUtil.success(records, list.getTotal());
    }

    @RequestMapping(value = "/addMember",method = RequestMethod.POST)
    public Result<String> addMember(@RequestBody Map<String, Object> map){
        //创建member对象
        Member member = new Member();
        member.setMemberCode(map.get("member_code").toString());
        member.setMName(map.get("m_name").toString());
        member.setMGender(map.get("m_gender").toString());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        member.setMBirthday(LocalDate.parse(map.get("m_birthday").toString(), fmt));
        member.setMPhone(map.get("m_phone").toString());
        String mcName = map.get("mcName").toString();
        member.setMcId(memberCategoryService.getIdByName(mcName));
        Result<String> result = new Result<>();
        //判断是否存在
        if(memberService.memberExist(member.getMemberCode())){
            return ResultUtil.error(ResultEnum.USER_IS_EXIST);
        }
        else {//判断是否添加成功
            if(memberService.addMember(member)){
                return ResultUtil.success();
            }
            else {
                return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
            }
        }
    }

    @RequestMapping(value = "/editMember", method = RequestMethod.POST)
    public Result editMember(@RequestBody Map<String, Object> map){
        //创建member对象
        Member member = new Member();
        member.setMName(map.get("m_name").toString());
        member.setMGender(map.get("m_gender").toString());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        member.setMBirthday(LocalDate.parse(map.get("m_birthday").toString(), fmt));
        member.setMPhone(map.get("m_phone").toString());
        Long memberId = Long.valueOf(map.get("member_id").toString());
        //判断是否修改成功
        if(memberService.editMember(memberId, member)){
            return ResultUtil.success();
        }
        else return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public Result deleteMember(@RequestBody Map<String, Object> map){
        String memberId = map.get("member_id").toString();

        if(memberService.deleteMemberById(memberId)){
            return ResultUtil.success();
        }
        else return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

    @RequestMapping(value = "getMemberByMemberCode", method = RequestMethod.GET)
    public Result<Member> findMemberByMemberCode(@RequestParam(value = "member_code") String memberCode){
        Result<Member> result = new Result<>();
        Member member = memberService.getMemberByMemberCode(memberCode);
        if(member != null){
            return ResultUtil.success(member);
        }
        else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

}
