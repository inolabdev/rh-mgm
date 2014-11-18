package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Image;
import mz.inolabdev.rh.services.ImageService;

import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl extends GenericServiceImpl<Image>
		implements ImageService {

	//We can also override functions to specify some logics.
}
