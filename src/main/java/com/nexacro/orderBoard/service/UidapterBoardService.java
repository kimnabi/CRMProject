package com.nexacro.orderBoard.service;

import java.util.List;
import java.util.Map;

import com.nexacro.orderBoard.object.Board;

/**
 * <pre>
 * 
 * @title
 * @desc 아래의 예제는 샘플용으로 작성된 코드로 참고용으로만 사용하시기 바랍니다. -
 * @package com.nexacro.orderBoard.service
 * 
 *          <pre>
 * 
 * @author TOBESOFT
 * @since 2017. 11. 20.
 * @version 1.0
 * @see =================== 변경 내역 ================== 날짜 변경자 내용
 *      ------------------------------------------------ 2017. 11. 20. TOBESOFT
 *      최초작성
 */
public interface UidapterBoardService {

	List<Map<String, Object>> retrieve_datalist_map(Map<String, String> board);

	List<Board> retrieve_datalist(Board board);

	void update_datalist(List<Board> boardList);

	void update_datalist_map(List<Map<String, Object>> boardList);
	
	int selectUserCount();
}