package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Lllei on 2018/11/8.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
