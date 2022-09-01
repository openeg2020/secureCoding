package kr.co.openeg.lab.board.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.openeg.lab.board.model.BoardCommentModel;
import kr.co.openeg.lab.board.model.BoardModel;
import kr.co.openeg.lab.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService service;

	//
	// * User variable
	// article, page variables
	private int currentPage = 1;
	private int showArticleLimit = 10; // change value if want to show more articles by one page
	private int showPageLimit = 10; // change value if want to show more page links
	private int startArticleNum = 0;
	private int endArticleNum = 0;
	private int totalNum = 0;
	//
	// file upload path
	// private String uploadPath =
	// "D:\\Workspace\\SummerBoard\\WebContent\\files\\";
	//
	//

	@RequestMapping("/main")
	public String login_success_main(HttpServletRequest request) {
		return "/board/login_success_main";
	}

	@RequestMapping("/list")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {

		String type = null;
		String keyword = null;

		// set variables from request parameter
		if (request.getParameter("page") == null || request.getParameter("page").trim().isEmpty()
				|| request.getParameter("page").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		if (request.getParameter("type") != null) {
			type = request.getParameter("type").trim();
		}

		if (request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword").trim();
		}
		//

		// expression article variables value
		startArticleNum = (currentPage - 1) * showArticleLimit + 1;
		endArticleNum = startArticleNum + showArticleLimit - 1;
		//

		// get boardList and get page html code
		List<BoardModel> boardList;
		if (type != null && keyword != null) {
			boardList = service.searchArticle(type, keyword, startArticleNum, endArticleNum);
			totalNum = service.getSearchTotalNum(type, keyword);
		} else {
			boardList = service.getBoardList(startArticleNum, endArticleNum);
			totalNum = service.getTotalNum();
		}
		StringBuffer pageHtml = getPageHtml(currentPage, totalNum, showArticleLimit, showPageLimit, type, keyword);
		//

		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.addObject("pageHtml", pageHtml);
		mav.setViewName("/board/list");

		return mav;
	}

	// A method for Creating page html code
	private StringBuffer getPageHtml(int currentPage, int totalNum, int showArticleLimit, int showPageLimit,
			String type, String keyword) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;

		// expression page variables
		startPage = ((currentPage - 1) / showPageLimit) * showPageLimit + 1;
		lastPage = startPage + showPageLimit - 1;

		if (lastPage > totalNum / showArticleLimit) {
			lastPage = (totalNum / showArticleLimit) + 1;
		}
		//

		// create page html code
		// if: when no search
		if (type == null && keyword == null) {
			if (currentPage == 1) {
				pageHtml.append("<span>");
			} else {
				pageHtml.append("<span><a href=\"list?page=" + (currentPage - 1) + "\"><이전></a>&nbsp;&nbsp;");
			}

			for (int i = startPage; i <= lastPage; i++) {
				if (i == currentPage) {
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list?page=" + i + "\" class=\"page\">" + i + "</a>");
					pageHtml.append("&nbsp;</strong>");
				} else {
					pageHtml.append(".&nbsp;<a href=\"list?page=" + i + "\" class=\"page\">" + i + "</a>&nbsp;");
				}

			}
			if (currentPage == lastPage) {
				pageHtml.append(".</span>");
			} else {
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list?page=" + (currentPage + 1) + "\"><다음></a></span>");
			}
			//
			// else: when search
		} else {
			if (currentPage == 1) {
				pageHtml.append("<span>");
			} else {
				pageHtml.append("<span><a href=\"list?page=" + (currentPage - 1) + "&type=" + type + "&keyword="
						+ keyword + "\"><이전></a>&nbsp;&nbsp;");
			}

			for (int i = startPage; i <= lastPage; i++) {
				if (i == currentPage) {
					pageHtml.append(".&nbsp;<strong>");
					pageHtml.append("<a href=\"list?page=" + i + "&type=" + type + "&keyword=" + keyword
							+ "\" class=\"page\">" + i + "</a>&nbsp;");
					pageHtml.append("&nbsp;</strong>");
				} else {
					pageHtml.append(".&nbsp;<a href=\"list?page=" + i + "&type=" + type + "&keyword=" + keyword
							+ "\" class=\"page\">" + i + "</a>&nbsp;");
				}

			}
			if (currentPage == lastPage) {
				pageHtml.append("</span>");
			} else {
				pageHtml.append(".&nbsp;&nbsp;<a href=\"list?page=" + (currentPage + 1) + "&type=" + type + "&keyword="
						+ keyword + "\"><다음></a></span>");
			}
		}
		//
		return pageHtml;
	}

	@RequestMapping("/view")
	public ModelAndView boardView(HttpServletRequest request) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		BoardModel board = service.getOneArticle(idx); // get selected article model
		service.updateHitcount(board.getHitcount() + 1, idx); // update hitcount

		List<BoardCommentModel> commentList = service.getCommentList(idx); // get comment list

		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("commentList", commentList);
		mav.setViewName("board/view");
		return mav;
	}

	@RequestMapping("/write")
	public String boardWrite(@ModelAttribute("BoardModel") BoardModel boardModel) {
		return "/board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String boardWriteProc(@ModelAttribute("BoardModel") BoardModel boardModel,
			MultipartHttpServletRequest request, HttpSession session) {
		// get upload file
		String uploadPath = session.getServletContext().getRealPath("/") + "files/";
		System.out.println("uploadPath: " + uploadPath);

		MultipartFile file = request.getFile("file");
		if (file != null && !"".equals(file.getOriginalFilename())) {
			String fileName = file.getOriginalFilename();
			File uploadFile = new File(uploadPath + fileName);

			// 폴더 경로
			File Folder = new File(uploadPath);

			// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
			if (!Folder.exists()) {
				try {
					Folder.mkdir(); // 폴더 생성합니다.
					System.out.println("폴더가 생성되었습니다.");
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			
			// 파일 업로드 실행
			if (uploadFile.exists()) {
				fileName = new Date().getTime() + fileName;
				uploadFile = new File(uploadPath + fileName);
				System.out.println("파일 업로드 경로: " + uploadPath + fileName);
			}
			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				System.out.println("upload error");
				e.printStackTrace();
			}
			boardModel.setFileName(fileName);
		}

		String content = boardModel.getContent().replaceAll("\r\n", "<br />");
		boardModel.setContent(content);

		service.writeArticle(boardModel);

		return "redirect:list";
	}

	@RequestMapping("/commentWrite")
	public ModelAndView commentWriteProc(@ModelAttribute("CommentModel") BoardCommentModel commentModel) {
		// new line code change to <br /> tag
		String content = commentModel.getContent().replaceAll("\r\n", "<br />");
		commentModel.setContent(content);
		//
		service.writeComment(commentModel);
		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", commentModel.getLinkedArticleNum());
		mav.setViewName("redirect:view");

		return mav;
	}

	@RequestMapping("/modify")
	public ModelAndView boardModify(@RequestParam int idx, HttpServletRequest request, HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		BoardModel board = service.getOneArticle(idx);
		// <br /> tag change to new line code
		String content = board.getContent().replaceAll("<br />", "\r\n");
		board.setContent(content);
		//

		ModelAndView mav = new ModelAndView();

		if (!userId.equals(board.getWriterId())) {
			mav.addObject("errCode", "1"); // forbidden connection
			mav.addObject("idx", idx);
			mav.setViewName("redirect:view");
		} else {
			mav.addObject("board", board);
			mav.setViewName("board/modify");
		}

		return mav;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView boardModifyProc(@ModelAttribute("BoardModel") BoardModel boardModel,
			MultipartHttpServletRequest request) {
		String uploadPath = request.getContextPath() + "/files/";
		String orgFileName = request.getParameter("orgFile");
		MultipartFile newFile = request.getFile("newFile");
		String newFileName = newFile.getOriginalFilename();

		boardModel.setFileName(orgFileName);

		// if: when want to change upload file
		if (newFile != null && !newFileName.equals("")) {
			if (orgFileName != null || !orgFileName.equals("")) {
				// remove uploaded file
				File removeFile = new File(uploadPath + orgFileName);
				removeFile.delete();
				//
			}
			// create new upload file
			File newUploadFile = new File(uploadPath + newFileName);
			try {
				newFile.transferTo(newUploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			boardModel.setFileName(newFileName);
		}
		// new line code change to <br /> tag
		String content = boardModel.getContent().replaceAll("\r\n", "<br />");
		String subject = boardModel.getSubject().replaceAll("\r\n", "<br />");
		boardModel.setContent(content);
		boardModel.setSubject(subject);

		service.modifyArticle(boardModel);

		ModelAndView mav = new ModelAndView();
		mav.addObject("idx", boardModel.getIdx());
		mav.setViewName("redirect:/board/view");
		return mav;
	}

	@RequestMapping("/delete")
	public ModelAndView boardDelete(HttpServletRequest request, HttpSession session) {
		
		String uploadPath = request.getContextPath() + "/files/";
		System.out.println("uploadPath: " + uploadPath);
		String userId = (String) session.getAttribute("userId");
		int idx = Integer.parseInt(request.getParameter("idx"));

		BoardModel board = service.getOneArticle(idx);

		ModelAndView mav = new ModelAndView();

		if (!userId.equals(board.getWriterId())) {
			mav.addObject("errCode", "1"); // it's forbidden connection
			mav.addObject("idx", idx);
			mav.setViewName("redirect:view");
		} else {
			List<BoardCommentModel> commentList = service.getCommentList(idx); // check comments
			if (commentList.size() > 0) {
				mav.addObject("errCode", "2"); // can't delete because a article has comments
				mav.addObject("idx", idx);
				mav.setViewName("redirect:view");
			} else {
				// if: when the article has upload file - remove that
				if (board.getFileName() != null) {
					File removeFile = new File(uploadPath + board.getFileName());
					removeFile.delete();
				}
				//
				service.deleteArticle(idx);

				mav.setViewName("redirect:list");
			}
		}
		return mav;
	}

	@RequestMapping("/commentDelete")
	public ModelAndView commendDelete(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int linkedArticleNum = Integer.parseInt(request.getParameter("linkedArticleNum"));

		String userId = (String) session.getAttribute("userId");
		BoardCommentModel comment = service.getOneComment(idx);

		ModelAndView mav = new ModelAndView();

		if (!userId.equals(comment.getWriterId())) {
			mav.addObject("errCode", "1");
		} else {
			service.deleteComment(idx);
		}

		mav.addObject("idx", linkedArticleNum); // move back to the article
		mav.setViewName("redirect:view");

		return mav;
	}

	@RequestMapping("/recommend")
	public ModelAndView updateRecommendcount(HttpServletRequest request, HttpSession session) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String userId = (String) session.getAttribute("userId");
		BoardModel board = service.getOneArticle(idx);

		ModelAndView mav = new ModelAndView();

		if (userId.equals(board.getWriterId())) {
			mav.addObject("errCode", "1");
		} else {
			service.updateRecommendCount(board.getRecommendcount() + 1, idx);
		}

		mav.addObject("idx", idx);
		mav.setViewName("redirect:/board/view");

		return mav;
	}
}
