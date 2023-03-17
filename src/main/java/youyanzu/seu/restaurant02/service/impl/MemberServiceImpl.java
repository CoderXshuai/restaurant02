package youyanzu.seu.restaurant02.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youyanzu.seu.restaurant02.entity.Member;
import youyanzu.seu.restaurant02.mapper.MemberMapper;
import youyanzu.seu.restaurant02.service.IMemberService;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Autowired
    private MemberMapper mapper;

    @Override
    public Page<Member> getList(int pageNum, int pageSize, Member member) {
        Page<Member> page = new Page<>();
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        if (member.getMName() != null)
            wrapper.eq("m_name", member.getMName());
        if (member.getMemberCode() != null)
            wrapper.eq("member_code", member.getMemberCode());
        if (member.getMGender() != null)
            wrapper.eq("m_gender", member.getMGender());
        if (member.getMPhone() != null)
            wrapper.eq("m_phone", member.getMPhone());
        if (member.getMcId() != null)
            wrapper.eq("mc_id", member.getMcId());
        if (member.getMBirthday() != null)
            wrapper.eq("m_birthday", member.getMBirthday());
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Page<Member> userPage = mapper.selectPage(page, wrapper);

        return userPage;
    }

    @Override
    public boolean memberExist(String memberCode) {
        Member member = null;
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("member_code", memberCode);
        member = mapper.selectOne(wrapper);
        return member != null;
    }

    @Override
    public boolean addMember(Member member) {
        int i = 0;
        LocalDateTime now = LocalDateTime.now();
        member.setCreateTime(now);
        i = mapper.insert(member);
        return i == 1;
    }

    @Override
    public boolean editMember(String loginCode, Member member) {
        int i = 0;
        LocalDateTime now = LocalDateTime.now();
        member.setModifyTime(now);
        UpdateWrapper<Member> wrapper = new UpdateWrapper<>();
        wrapper.eq("login_code", loginCode);
        i = mapper.update(member, wrapper);
        return i == 1;
    }


    @Override
    public boolean deleteMemberById(String memberId) {
        int i = 0;
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", memberId);
        i = mapper.delete(wrapper);
        return i == 1;
    }

    @Override
    public Member getMemberByMemberCode(String memberCode) {
        Member member = null;
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("member_code", memberCode);
        member = mapper.selectOne(wrapper);
        return member;
    }
}
