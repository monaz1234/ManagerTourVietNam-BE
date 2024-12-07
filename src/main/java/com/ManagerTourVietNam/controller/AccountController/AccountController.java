package com.ManagerTourVietNam.controller.AccountController;

import com.ManagerTourVietNam.model.Account;
import com.ManagerTourVietNam.model.User;
import com.ManagerTourVietNam.repository.AccountRepository;
import com.ManagerTourVietNam.repository.TypeTourRepository.TypeTourRepository;
import com.ManagerTourVietNam.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;

import java.io.File;
import java.io.IOException;

import java.util.*;

import java.util.Collections;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private TypeTourRepository typeTourRepository;
    String dirIUploadImageAccount = System.getProperty("user.dir") + "/public/image/account/";

    @GetMapping("api/accounts")
    public List<Account> GetAllAccount() {
        return accountService.getAllAccounts();
    }

    @PostMapping("api/account")
    public Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PutMapping("api/account/{id}")
    public Account editAccount(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("api/account/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("api/account/{id}")
    public Optional<Account> findAccountById(@PathVariable String id) {
        return accountService.findAccountById(id);
    }

    // Lấy danh sách người dùng với phân trang
    @GetMapping("/api/account/phantrang")
    public ResponseEntity<Page<Account>> getAccount(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize); // Spring Data bắt đầu từ 0
        Page<Account> pageAccounts = accountRepository.findAll(pageable);
        return ResponseEntity.ok(pageAccounts);
    }

    @GetMapping("/api/account-ids")
    public List<String> getAllAccountIds() {
        return accountRepository.findAll().stream()
                .map(Account::getIdaccount)
                .collect(Collectors.toList());
    }

    @GetMapping("api/accounts/search")
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam String query) {
        List<Account> accounts = accountService.searchAccounts(query);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }




    // @PostMapping("/api/account/login")
    // public ResponseEntity<?> login(@RequestBody Account account) {
    // Optional<Account> foundAccount =
    // accountService.findByUsernameAndPassword(account.getUsername(),
    // account.getPassword());
    // if (foundAccount.isPresent()) {
    // return ResponseEntity.ok(foundAccount.get());
    // } else {
    // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng
    // nhập không chính xác.");
    // }
    // }

//    @PostMapping("/api/account/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> loginPayload) {
//        String username = loginPayload.get("username");
//        String password = loginPayload.get("password");
//
//        Account account = accountService.validateLogin(username, password);
//        if (account != null) {
//            return ResponseEntity.ok(account);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không chính xác.");
//        }
//    }

    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginPayload) {
        String username = loginPayload.get("username");
        String password = loginPayload.get("password");

        // Gọi service để xác thực tài khoản
        Account account = accountService.validateLogin(username, password);
        System.out.println("Login response: " + account.getIdaccount());

        if (account != null) {
            // Lấy thông tin idtypeuser dựa trên username
            Optional<String> idTypeUser = accountService.getIdTypeUserByUsername(username);

            // Kiểm tra và trả về dữ liệu
            if (idTypeUser.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("account", account);
                response.put("idTypeUser", idTypeUser.get());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Không tìm thấy loại người dùng cho tài khoản này.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin đăng nhập không chính xác.");
        }
    }

//    @PostMapping("/api/account/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> loginPayload) {
//        String username = loginPayload.get("username");
//        String password = loginPayload.get("password");
//
//        // Xác thực tài khoản
//        Account account = accountService.validateLogin(username, password);
//
//        if (account != null) {
//            // Trả về thông tin tài khoản cơ bản
//            Map<String, Object> response = new HashMap<>();
//            response.put("idaccount", account.getIdaccount());
//            response.put("username", account.getUsername());
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Thông tin đăng nhập không chính xác.");
//        }
//    }






    @GetMapping("/api/accounts/iduser")
    public ResponseEntity<?> getIdUserByUsername(@RequestParam String username) {
        Optional<String> idUser = accountService.findIdUserByUsername(username);
        if (idUser.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("iduser", idUser.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }    }

    //lay hinh anh
    @GetMapping("/api/account/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName){
        Resource resource = resourceLoader.getResource("file:" + dirIUploadImageAccount + imageName);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // upload hinh anh account vao folder image/account
    @PostMapping("/api/account/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("accountName") String accountName) {
        System.out.println("accountName: " + accountName); // Log giá trị account
        // Kiểm tra nếu file không rỗng
        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "Vui lòng chọn một file để upload."));
        }

        try {
            // Lấy tên file từ vehicleName và gán định dạng PNG hoặc JPG
            String fileName = "img_" + accountName + ".png"; // hoặc JPG nếu cần

            // Lưu file vào thư mục

            File uploadDirFile = new File(dirIUploadImageAccount);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            File destinationFile = new File(dirIUploadImageAccount + fileName);
            file.transferTo(destinationFile);

            // Cập nhật tên file vào cơ sở dữ liệu (cột image trong bảng Account)
            Optional<Account> accountOptional = accountService.findAccountById(accountName);
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                account.setImage(fileName); // Set tên file vào tài khoản

                // Lưu cập nhật vào cơ sở dữ liệu
                accountService.updateAccount(account.getIdaccount(), account);
            } else {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("message", "Tài khoản không tồn tại."));
            }

            return ResponseEntity.ok(Collections.singletonMap("message", "Tải lên thành công: " + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi tải lên hình ảnh: " + e.getMessage()));
        }

    }

    @DeleteMapping("/api/account/images/{imageName}")
    public ResponseEntity<?> deleteImage(@PathVariable String imageName) {
        File file = new File(dirIUploadImageAccount + imageName);

        // Kiểm tra xem file có tồn tại không
        if (!file.exists()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu hình ảnh không tồn tại
        }

        // Thực hiện xóa file
        if (file.delete()) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Xóa hình ảnh thành công: " + imageName));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Đã xảy ra lỗi khi xóa hình ảnh."));
        }
    }



    @GetMapping("/api/account/{iduser}/idaccount")
    public ResponseEntity<String> getIdAccountByIduserNew(@PathVariable String iduser) {
        // Tìm account dựa trên iduser
        Optional<Account> accountOptional = accountRepository.findByUserIduser(iduser);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return ResponseEntity.ok(account.getIdaccount()); // Trả về idaccount
        }
        return ResponseEntity.notFound().build(); // Nếu không tìm thấy account
    }

    private String getIdUserFromAccount(Account account) {
        if (account.getUser() != null) {
            return account.getUser().getIduser();
        }
        return null;
    }

    @GetMapping("/api/account/{accountId}/iduser")
    public ResponseEntity<Map<String, String>> getIdUserByAccountId(@PathVariable String accountId) {
        String idUser = accountService.getIdUserByAccountId(accountId);
        if (idUser != null) {
            Map<String, String> response = new HashMap<>();
            response.put("iduser", idUser); // Trả về iduser dưới dạng JSON
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }


        @GetMapping("api/account/username/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable String username) {
        try {
            Account account = accountService.getAccountByUsername(username);
            return ResponseEntity.ok(account);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }







}
