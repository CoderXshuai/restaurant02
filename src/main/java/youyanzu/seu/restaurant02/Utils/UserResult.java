package youyanzu.seu.restaurant02.Utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import youyanzu.seu.restaurant02.entity.SysUser;
import youyanzu.seu.restaurant02.service.ISysRoleService;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author summerWang
 * @since 2023-03-14
 */
@Data
public class UserResult {



    /**
     * 用户id
     */

    private Long userId;

    /**
     * 登录账户
     */
    private String loginCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String roleName;
    private int roleId;
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 删除标志
     */
    private Integer del;


}
