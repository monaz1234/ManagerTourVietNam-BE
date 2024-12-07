package com.ManagerTourVietNam.controller.AuthController;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.Type_user;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.AccountRepository;
import com.ManagerTourVietNam.repository.UserRepository;
import com.ManagerTourVietNam.service.Account.AccountService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    @Autowired
    private AccountService accountService;
    @Value("${google.client-id}")
    private String clientId;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("api/auth/google")
    public ResponseEntity<?> verifyGoogleToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                System.out.println(idToken); // Token không hợp lệ
                // Lấy thông tin user từ token
                String userId = payload.getSubject();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String picture = (String) payload.get("picture");

                // Log thông tin người dùng
//                System.out.println("Thông tin người dùng: ");
//                System.out.println("ID người dùng: " + userId);
//                System.out.println("Email: " + email);
//                System.out.println("Tên: " + name);
//                System.out.println("Ảnh đại diện: " + picture);
                // Kiểm tra email đã tồn tại chưa
                if (!userRepository.existsByEmail(email)) {
                    createUser(name,email);
                }
                // Trả về thông tin user
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("userId", userId);
                userInfo.put("email", email);
                userInfo.put("name", name);
                userInfo.put("picture", picture);
                return ResponseEntity.ok(userInfo);
            } else {
                System.out.println("Invalid token"); // Token không hợp lệ
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi chi tiết
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token verification failed");
        }

    }
    private  void createUser(String name,String email){
        // Tạo user mới
        User newUser = new User();
        newUser.setIduser(generateNewUserId()); // Tạo ID tự động
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPoints(0); // Giá trị mặc định
        newUser.setReward(0); // Giá trị mặc định
        newUser.setSalary(0); // Giá trị mặc định
        newUser.setStatus(1); // Giá trị mặc định
        // Tạo Type_user mặc định có id "T001"
        Type_user defaultTypeUser = new Type_user();
        defaultTypeUser.setIdtypeuser("T001"); // Gán id mặc định là "T001"
        newUser.setTypeUser(defaultTypeUser); // Gán vào User

        userRepository.save(newUser); // Lưu người dùng mới vào cơ sở dữ liệu
        createAccount(newUser);
    }
    private void createAccount(User user){
        Account newAccount = new Account();
        newAccount.setIdaccount(generateNewAccountId());
        newAccount.setUsername(user.getEmail());
        newAccount.setPassword(generateRandomNumber(8));
        newAccount.setStatus(1);
        // Tạo Type_user mặc định có id "T001"
        Type_user defaultTypeUser = new Type_user();
        defaultTypeUser.setIdtypeuser("T003"); // Gán id mặc định là "T003"
        newAccount.setTypeUser(defaultTypeUser); // Gán vào User
        newAccount.setUser(user);

        accountRepository.save(newAccount);
    }
    // Phương thức để tạo ID người dùng mới (ví dụ U001, U002, U003, ...)
    private String generateNewUserId() {
        // Lấy tất cả các ID người dùng hiện tại và tách phần số của ID
        List<String> existingIds = userRepository.findAll()
                .stream()
                .map(user -> user.getIduser().substring(1)) // Lấy phần số của ID (Bỏ chữ "U")
                .collect(Collectors.toList());

        // Chuyển các phần số ID thành kiểu số nguyên
        Set<Integer> existingNumbers = existingIds.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        // Tìm số ID nhỏ nhất bị thiếu
        int newId = 1;
        while (existingNumbers.contains(newId)) {
            newId++;
        }

        // Tạo ID mới theo định dạng Uxxx (với số nhỏ nhất không có trong cơ sở dữ liệu)
        return "U" + String.format("%03d", newId);
    }

    // Phương thức để tạo ID tài khoản mới (ví dụ A001, A002, A003, ...)
    private String generateNewAccountId() {
        // Lấy tất cả các ID tài khoản hiện tại và tách phần số của ID
        List<String> existingIds = accountRepository.findAll()
                .stream()
                .map(account -> account.getIdaccount().substring(1)) // Lấy phần số của ID (Bỏ chữ "A")
                .collect(Collectors.toList());

        // Chuyển các phần số ID thành kiểu số nguyên
        Set<Integer> existingNumbers = existingIds.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        // Tìm số ID nhỏ nhất bị thiếu
        int newId = 1;
        while (existingNumbers.contains(newId)) {
            newId++;
        }

        // Tạo ID mới theo định dạng Axxx (với số nhỏ nhất không có trong cơ sở dữ liệu)
        return "A" + String.format("%03d", newId);
    }
    // Phương thức tạo chuỗi số ngẫu nhiên có độ dài "length"
    private  String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));  // Thêm một chữ số ngẫu nhiên (0 đến 9)
        }

        return sb.toString();  // Trả về chuỗi 8 số
    }

}
