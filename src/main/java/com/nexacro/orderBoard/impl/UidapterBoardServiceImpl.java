package com.nexacro.orderBoard.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexacro.orderBoard.mapper.UiadapterBoardMapper;
import com.nexacro.orderBoard.object.Board;
import com.nexacro.orderBoard.service.UidapterBoardService;
import com.nexacro.uiadapter.spring.core.data.DataSetRowTypeAccessor;
import com.nexacro.java.xapi.data.DataSet;

/**
 * <pre>
 * 
 * @title
 * @desc 아래의 예제는 샘플용으로 작성된 코드로 참고용으로만 사용하시기 바랍니다. - UidapterBoardServiceImpl
 *       Sample Class
 * @package com.nexacro.orderBoard.impl
 * 
 *          <pre>
 * 
 * @author TOBESOFT
 * @since 2018. 1. 25.
 * @version 1.0
 * @see =================== 변경 내역 ================== 날짜 변경자 내용
 *      ------------------------------------------------ 2018. 1. 25. TOBESOFT
 *      최초작성
 */
@Service
public class UidapterBoardServiceImpl implements UidapterBoardService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Board> retrieve_datalist(Board board) {
		UiadapterBoardMapper mapper = sqlSession.getMapper(UiadapterBoardMapper.class);
		return mapper.retrieve_datalist(board);
	}

	@Override
	public List<Map<String, Object>> retrieve_datalist_map(Map<String, String> board) {
		UiadapterBoardMapper mapper = sqlSession.getMapper(UiadapterBoardMapper.class);
		return mapper.retrieve_datalist_map(board);
	}

	@Override
	public void update_datalist(List<Board> boardList) {
		UiadapterBoardMapper mapper = sqlSession.getMapper(UiadapterBoardMapper.class);

		int size = boardList.size();
		for (int i = 0; i < size; i++) {
			Board board = boardList.get(i);
			if (board instanceof DataSetRowTypeAccessor) {
				DataSetRowTypeAccessor accessor = board;

				if (accessor.getRowType() == DataSet.ROW_TYPE_INSERTED) {
					mapper.insert_board(board);
				} else if (accessor.getRowType() == DataSet.ROW_TYPE_UPDATED) {
					mapper.update_board(board);
				} else if (accessor.getRowType() == DataSet.ROW_TYPE_DELETED) {
					mapper.delete_board(board);
				}
			}
		}
	}

	@Override
	public void update_datalist_map(List<Map<String, Object>> boardList) {
		UiadapterBoardMapper mapper = sqlSession.getMapper(UiadapterBoardMapper.class);

		int size = boardList.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> board = boardList.get(i);

			int rowType = Integer.parseInt(String.valueOf(board.get(DataSetRowTypeAccessor.NAME)));
			if (rowType == DataSet.ROW_TYPE_INSERTED) {
				mapper.insert_board_map(board);
			} else if (rowType == DataSet.ROW_TYPE_UPDATED) {
				mapper.update_board_map(board);
			} else if (rowType == DataSet.ROW_TYPE_DELETED) {
				mapper.delete_board_map(board);
			}
		}
	}
	
	
	@Override
	public int selectUserCount() {
		UiadapterBoardMapper mapper = sqlSession.getMapper(UiadapterBoardMapper.class);
		
		return mapper.selectUserCount();
	}
}
