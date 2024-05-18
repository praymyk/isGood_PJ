package com.ys.isGood.common.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageInfo {
	private int listCount; 		// 게시글의 총 개수
	private int currentPage; 	// 사용자가 요청한 페이지수
	private int pageLimit; 		// 페이징바의 숫자 개수
	private int boardLimit; 	// 한 페이지에 보여질 게시글의 개수
	
	private int maxPage; 		// 마지막 페이지수
	private int startPage; 		// 페이징바의 시작수
	private int endPage; 		// 페이징바의 끝수

}
