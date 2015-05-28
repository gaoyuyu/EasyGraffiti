<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
	<head>
		<link href="css/main.css" rel="stylesheet" type="text/css" />
		<link href="css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />

		<!-- add scripts -->
		<script src="js/jquery.min.js">
</script>
		<script src="js/jquery.Jcrop.min.js">
</script>
		<script src="js/script.js">
</script>
	</head>

	<body>
		<div class="demo" style="margin-top: 60px;">
			<div class="bheader">
				<h2>
					――选择上传的图片――
				</h2>
			</div>
			<div class="bbody">

				<!-- upload form -->
				<form id="upload_form" enctype="multipart/form-data" method="post"
					action="upload" onsubmit="checkForm()">
					<!-- hidden crop params -->
					<input type="hidden" id="x1" name="x1" />
					<input type="hidden" id="y1" name="y1" />
					<input type="hidden" id="x2" name="x2" />
					<input type="hidden" id="y2" name="y2" />

					<h2>
						第一步:请选择图像文件
					</h2>
					<div>
						<input type="file" name="image_file" id="image_file"
							onchange="fileSelectHandler()" />
					</div>

					<div class="error"></div>

					<div class="step2">
						<h2>
							请选择需要截图的部位,然后按上传
						</h2>
						<img id="preview" />

						<div class="info">
							<label>
								文件大小
							</label>
							<input type="text" id="filesize" name="filesize" />
							<label>
								类型
							</label>
							<input type="text" id="filetype" name="filetype" />
							<label>
								图像尺寸
							</label>
							<input type="text" id="filedim" name="filedim" />
							<label>
								宽度
							</label>
							<input type="text" id="w" name="fileWeight" />
							<label>
								高度
							</label>
							<input type="text" id="h" name="fileHeight" />
						</div>

						<input type="submit" value="上传" />
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
