package youyanzu.seu.restaurant02.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import youyanzu.seu.restaurant02.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author summerWang
 * @since 2023-03-13
 */
public interface IMemberService extends IService<Member> {
    Page<Member> getList(int pageNum, int pageSize, Member member);
    boolean memberExist(String memberCode);
    boolean addMember(Member member);
    boolean editMember(String loginCode, Member member);
    boolean deleteMemberById(String memberId);
    Member getMemberByMemberCode(String memberCode);

}
