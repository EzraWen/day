package com.ezra.service.impl;

import com.ezra.entity.File;
import com.ezra.mapper.FileMapper;
import com.ezra.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ezra Wen
 * @since 2021-03-08
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
