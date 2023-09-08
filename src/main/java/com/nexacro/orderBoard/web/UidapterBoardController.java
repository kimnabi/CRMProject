package com.nexacro.orderBoard.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataSetList;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.Variable;
import com.nexacro.java.xapi.data.VariableList;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.orderBoard.object.Board;
import com.nexacro.orderBoard.service.UidapterBoardService;
import com.nexacro.uiadapter.spring.core.NexacroException;
import com.nexacro.uiadapter.spring.core.annotation.ParamDataSet;
import com.nexacro.uiadapter.spring.core.annotation.ParamVariable;
import com.nexacro.uiadapter.spring.core.data.NexacroFirstRowHandler;
import com.nexacro.uiadapter.spring.core.data.NexacroResult;

/**
 * <pre>
 * 
 * @title
 * @desc 아래의 예제는 샘플용으로 작성된 코드로 참고용으로만 사용하시기 바랍니다. - UidapterBoardController
 *       Sample Class
 * @package com.nexacro.orderBoard.web
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
@Controller
public class UidapterBoardController {

	private Logger log = LoggerFactory.getLogger(UidapterBoardController.class);

	@Autowired(required = true)
	private UidapterBoardService uidapterSampleService;
	
	
	@RequestMapping(value = "/orderBoard/healthCheck.do")
	public NexacroResult healthCheck() throws NexacroException {
		NexacroResult result = new NexacroResult();
		HashMap<String,Object> dsList = new HashMap<String,Object>();
		int userCount = uidapterSampleService.selectUserCount();
		dsList.put("result",userCount);
		
		result.addDataSet("dsList", dsList);
		
		System.out.println("__ok__" + dsList.size());
		return result;
	}
	
	
	/**
	 * 
	 * <pre>
	 * &#64;desc 리스트 데이터 조회 - VO
	 * &#64;param  
	 * &#64;return NexacroResult
	 * &#64;throws
	 * </pre>
	 */
	@RequestMapping(value = "/retrieve_datalist.do")
	public NexacroResult selectDataListVO(@ParamDataSet(name = "dsSearch", required = false) Board board)
			throws NexacroException {
		List<Board> boardList = uidapterSampleService.retrieve_datalist(board);

		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", boardList);

		return result;
	}

	/**
	 * 
	 * <pre>
	 * &#64;desc 리스트 데이터 조회 - VO
	 * &#64;param  
	 * &#64;return NexacroResult
	 * &#64;throws
	 * </pre>
	 */
	@RequestMapping(value = "/retrieve_datalist2.do")
	public NexacroResult selectDataListVO2(@ParamDataSet(name = "dsSearch", required = false) Board board, PlatformData platformData )
			throws NexacroException {
		log.debug("retrieve_datalist.do :: platformData : " + platformData.saveXml());
		List<Board> boardList = uidapterSampleService.retrieve_datalist(board);

		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", boardList);

		return result;
	}

	/**
	 * 리스트 데이터 조회 - MAP
	 * 
	 * @param searchVo
	 * @return
	 */
	@RequestMapping(value = "/retrieve_datalist_map.do")
	public NexacroResult selectDataListMap(@ParamDataSet(name = "dsSearch", required = false) Map<String, String> board)
			throws NexacroException {

		List<Map<String, Object>> boardList = uidapterSampleService.retrieve_datalist_map(board);

		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", boardList);

		return result;
	}

	/**
	 * 리스트 데이터 입력,수정,삭제
	 * 
	 * @param modifyList
	 * @return
	 */
	@RequestMapping(value = "/update_datalist.do")
	public NexacroResult updateDataListVo(@ParamDataSet(name = "input1") List<Board> boardList)
			throws NexacroException {

		uidapterSampleService.update_datalist(boardList);

		NexacroResult result = new NexacroResult();

		return result;
	}

	/**
	 * 리스트 데이터 입력,수정,삭제
	 * 
	 * @param modifyList
	 * @return
	 */
	@RequestMapping(value = "/update_datalist_map.do")
	public NexacroResult updateDataListMap(@ParamDataSet(name = "input1") List<Map<String, Object>> boardList)
			throws NexacroException {

		uidapterSampleService.update_datalist_map(boardList);

		NexacroResult result = new NexacroResult();

		return result;
	}

	/**
	 * uiadapter 처리 parameter 종류
	 * 
	 * @param unitList
	 * @param unitMapList
	 * @param dsUnit
	 * @param iVar1
	 * @param iVar2
	 * @param sVar1
	 * @param sVar2
	 * @param dsList
	 * @param varList
	 * @param platformRequest
	 * @param platformResponse
	 * @param firstRowHandler
	 * @return
	 */
	@RequestMapping(value = "/test.do")
	public NexacroResult test(
			@ParamDataSet(name = "dsUnit") List<Board> unitList,
			@ParamDataSet(name = "dsUnit") List<Map> unitMapList, 
			@ParamDataSet(name = "dsUnit") DataSet dsUnit, 
			@ParamVariable(name = "intValue") int intValue, 
			@ParamVariable(name = "stringValue") String stringValue,
			@ParamVariable(name = "intValue") Variable intVariable,
			@ParamVariable(name = "stringValue") Variable StringValue, 
			DataSetList dataSetList, 
			VariableList variableList, 
			PlatformData platformData,
			HttpPlatformRequest httpPlatformRequest,
			HttpPlatformResponse httpPlatformResponse, 
			NexacroFirstRowHandler firstRowHandler		) {

		if (log.isDebugEnabled()) {
			log.debug("debug_message : nexacro supported parameter types..");
		}

		// control nexacro result...
		NexacroResult result = new NexacroResult();
		result.addDataSet("dsUnitList", unitList);
		result.addVariable("responseInt", intVariable);
		result.addVariable("responseString", StringValue);
		return result;
	}

}
