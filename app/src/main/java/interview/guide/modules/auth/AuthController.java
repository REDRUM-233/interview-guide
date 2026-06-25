package interview.guide.modules.auth;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview.guide.common.result.Result;
import interview.guide.common.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            String token = jwtUtil.generateToken("1", "ADMIN");
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

}
