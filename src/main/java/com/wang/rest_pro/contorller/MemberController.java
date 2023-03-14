package com.wang.rest_pro.contorller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.rest_pro.Enums.ResultEnum;
import com.wang.rest_pro.Utils.ResultUtil;
import com.wang.rest_pro.entity.Member;
import com.wang.rest_pro.entity.MemberCategory;
import com.wang.rest_pro.entity.Result;
import com.wang.rest_pro.mapper.MemberCategoryMapper;
import com.wang.rest_pro.service.IMemberService;
import com.wang.rest_pro.service.IMemberCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Result<List<Member>> memberList(@RequestParam("page_num") int pageNum, @RequestParam("page_size")int pageSize,
                                           @RequestParam(value = "member_code", required = false) String memberCode,@RequestParam(value = "m_name", required = false) String mName,
                                           @RequestParam(value ="m_gender", required = false) String mGender,@RequestParam(value ="m_birthday", required = false) String mBirthday,
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
        Result<List<Member>> result = new Result<>();
        //封装结果
        result.setData(records);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setCount(list.getTotal());
        return result;
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
            result.setCode(ResultEnum.USER_IS_EXIST.getCode());
            result.setMsg(ResultEnum.USER_IS_EXIST.getMsg());
            return result;
        }
        else {//判断是否添加成功
            if(memberService.addMember(member)){
                result.setCode(ResultEnum.SUCCESS.getCode());
                result.setMsg(ResultEnum.SUCCESS.getMsg());
                return result;
            }
            else {
                result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
                result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
                return result;
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
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setData(member);
            return result;
        }
        else
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
    }

}
