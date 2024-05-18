
package com.ys.isGood.common.template;

import com.ys.isGood.common.model.vo.PageInfo;

public class Pagination {

	/**
	 * 전체 글의 수와 현재 페이지 번호를 이용하여 페이징 처리를 위한 페이지 정보를 생성하는 메서드
	 *
	 * @param listCount   전체 글의 수
	 * @param currentPage 현재 페이지 번호
	 * @param pageLimit   한 번에 보여줄 페이지 수
	 * @param boardLimit  페이지당 보여줄 글의 수
	 * @return 페이징 처리를 위한 페이지 정보 객체
	 */
	public static PageInfo getPageInfo(int listCount, int currentPage,
									   int pageLimit, int boardLimit) {

		// 전체 페이지 수 계산
		int maxPage = (int) Math.ceil((double) listCount / boardLimit);

		// 페이지 그룹에서 시작 페이지 번호 계산
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		// 페이지 그룹에서 마지막 페이지 번호 계산
		int endPage = startPage + pageLimit - 1;

		// 계산된 마지막 페이지 번호가 전체 페이지 수를 초과하는 경우 수정
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 계산된 값들을 이용하여 PageInfo 객체 생성하여 반환
		return new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
	}
}
