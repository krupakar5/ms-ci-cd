package io.msa.content.controller;

import java.io.BufferedInputStream;
import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;




import io.msa.content.models.Content;
import io.msa.content.services.ContentService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("/content")
public class ContentController {

	@Value("${msa.s3.bucketName}")
	private String BucketPath;

	@Value("${msa.s3.url}")
	private String endPointRoot;

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Content saveContent(@Valid @RequestBody Content content) {
		return contentService.save(content);

	}

	@RequestMapping(value = "/getctnbyid/{ctnId}", method = RequestMethod.GET)
	public List<Content> findByContentId(@PathVariable Long ctnId) {
		return contentService.fetchById(ctnId);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Iterable<Content> getAllContents() {
		return contentService.findAll();

	}

	@RequestMapping(value = "/findbyname/{name}", method = RequestMethod.GET)
	public List<Content> fetchByName(@PathVariable String name) {
		return contentService.fetchByName(name);
	}

	@RequestMapping(value = "/delete/{ctnId}", method = RequestMethod.DELETE)
	public void deleteByContentId(@PathVariable Long ctnId) {
		contentService.deleteById(ctnId);
	}

	@RequestMapping(value = "/uploadimage", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile multipartFile) {
		if (!multipartFile.isEmpty()) {
			try {
				String fileS3path = endPointRoot + "/" + BucketPath + "/" + multipartFile.getOriginalFilename();
				uploadfileToS3(multipartFile);
				JSONObject response = new JSONObject();
				response.put("imageUrl", fileS3path);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				return new ResponseEntity<String>(response.toString(), headers, HttpStatus.OK);
			} catch (Exception e) {
				JSONObject response = new JSONObject();
				response.put("error", "Unknown error from Content/uploadimage API" + e.toString());
				return new ResponseEntity<String>(response.toString(), HttpStatus.BAD_REQUEST);
			}

		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("deprecation")
	private void uploadfileToS3(MultipartFile inMultipartFile) throws Exception {
		AmazonS3 s3Client = new AmazonS3Client();
		String fileName = inMultipartFile.getOriginalFilename();
		s3Client.setEndpoint(endPointRoot);
		BufferedInputStream stream = new BufferedInputStream(inMultipartFile.getInputStream());
		PutObjectRequest putObjectRequest = new PutObjectRequest(BucketPath, fileName, stream, new ObjectMetadata());
		PutObjectResult result;
		try {
			result = s3Client.putObject(putObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("Etag:" + result.getETag() + "-->" + result);
	}
}
