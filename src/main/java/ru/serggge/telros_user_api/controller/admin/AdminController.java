package ru.serggge.telros_user_api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.controller.admin.dto.ViewAllUserDtoResponse;
import ru.serggge.telros_user_api.controller.admin.dto.ViewUserDtoResponse;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController implements AdminOperations {

    private final UserService userService;
    private final AdminMapper adminMapper;

    /**
     *
     * @param userId accepts user id
     * @return returns extended information about the user
     */
    @Override
    public ViewUserDtoResponse view(Long userId) {
        User user = userService.getByIdEager(userId);
        return adminMapper.toViewUserDtoResponse(user);
    }

    /**
     *
     * @return returns extended information about all users
     */
    @Override
    public List<ViewAllUserDtoResponse> viewAll() {
        List<User> allUsers = userService.getAllEager();
        return adminMapper.toViewAllUserDtoResponse(allUsers);
    }
}
