package mz.inolabdev.rh.viewModel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.util.Consts;

import org.zkoss.bind.BindContext;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;

public class FileManager {

	public Document upload(BindContext ctx) throws IOException {
		UploadEvent upEvent = null;

		Object objUploadEvent = ctx.getTriggerEvent();
		Document document = new Document();
		
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}

		if (upEvent != null) {

			Media media = upEvent.getMedia();
			String filePath = Executions.getCurrent().getDesktop().getWebApp()
					.getRealPath("/");
			File baseDir = new File(filePath);
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			Files.copy(new File(filePath + media.getName()),
					media.getStreamData());

			document.setFileName(media.getName());
			document.setFileType(media.getContentType());
			document.setContent(media.getByteData());
		}
		return document;
	}

	// this must be generic as it is! we can change this name
	public AMedia byteToFile(String name, String type, byte[] buffer) {
		ByteArrayInputStream is = new ByteArrayInputStream(buffer);
		String format = type.substring(type.indexOf("/") + Consts.ONE);
		AMedia fileContent = new AMedia(name, format, type, is);
		return fileContent;
	}
}
