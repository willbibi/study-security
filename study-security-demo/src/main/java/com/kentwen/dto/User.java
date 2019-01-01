package com.kentwen.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kentwen.validator.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by 毕文 on 2018/12/23.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public interface UserSimpleView {

    }

    public interface UserDetailView extends UserSimpleView {

    }

    @JsonView(UserSimpleView.class)
    private Integer id;

    @MyConstraint(message = "这是一个测试！")
    @JsonView(UserSimpleView.class)
    private String username;

    @NotBlank
    @JsonView(UserDetailView.class)
    private String password;

    @JsonView(UserSimpleView.class)
    @Past
    private Date birthday;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
