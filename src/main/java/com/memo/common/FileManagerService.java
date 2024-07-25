package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {
	
	//실제 업로드가 된 이미지가 저장될 서버의 경로
//	public static final String FILE_UPLOAD_PATH ="D:\\한주형\\6_spring_project\\memo\\memo_workspace\\images/";
	
	//집에서 사용되는 경로
	public static final String FILE_UPLOAD_PATH ="C:\\한주형2\\6_project\\memo\\workspace\\images/";
	
	//input: MultipartFile, userLoginId
	//output: String(이미지 경로)
	
	public String uploadFile(MultipartFile file, String loginId) {
		
		// 폴더(디렉토리) 생성
		// 예: aaaa_17348566965/sun.png
		String directoryName = loginId + "_" + System.currentTimeMillis();
		
		// D:\\한주형\\6_spring_project\\memo\\memo_workspace\\images/aaaa_17348566965/
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		//폴더 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 폴더 생성 시 실패하면 경로를 null로 리턴
			return null;
		}
		
		//파일 업로드 바이너리 파일 값
		try {
			byte[] bytes = file.getBytes();
			//★★★★★★★★ 한글 명으로 된 이미지는 업로드 불가하므로 나중에 영문자로 바꾸기
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 경로 null
		}
		
		// 파일 업로드가 성공하면 이미지 url path를 리턴
		// 주소는 이렇게 될 것이다.(예언)
		// /images/aaaa_17348566965/sun.png
		return "/image/" + directoryName +"/" +file.getOriginalFilename();
	}
	
	// 파일 삭제
	//input : 삭제할 파일 경로
	//output : X
	public void deleteFile(String imagePath) {
		//  /image/gks6141_1721209546244/space.gif
		//   D:\한주형\6_spring_project\memo\memo_workspace\images\gks6141_1721209546244/space.gif
		//   D:\\한주형\\6_spring_project\\memo\\memo_workspace\\images/
		//  주소에 겹치는 /images/ 지워야함
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		
		//삭제할 이미지가 존재하는가?
		if (Files.exists(path)) {
			//이미지 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.info("[FileManagerService 파일 삭제] 삭제 실패. path:{}",path.toString());
				return;
			}
			
			//폴더(디렉토리 삭제)
			path = path.getParent();
			if(Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.info("[FileManagerService 파일 삭제] 디렉토리 삭제 실패. path:{}",path.toString());
				}
			}
			
		}
	}
	
	
//	public static final String FILE_UPLOAD_PATH ="D:\\한주형\\6_spring_project\\memo\\memo_workspace\\images";
}
