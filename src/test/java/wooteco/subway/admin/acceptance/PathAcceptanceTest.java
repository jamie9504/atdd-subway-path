package wooteco.subway.admin.acceptance;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import wooteco.subway.admin.dto.LineResponse;
import wooteco.subway.admin.dto.StationResponse;

public class PathAcceptanceTest extends AcceptanceTest {

	/**
	 * Feature: 지하철 노선 경로 검색
	 * <p>
	 * Scenario: 지하철 노선 최단 거리 검색 Given 지하철역이 여러 개 추가되어 있다. And   지하철 노선이 여러 개 추가되어 있다. And   지하철 노선에
	 * 구간 정보가 여러 개 추가되어 있다.
	 * <p>
	 * When 지하철 노선 최단 거리 검색을 한다. Then 최단 거리 기준으로 경로와 거리 정보를 응답한다. And  거리 정보에는 총 소요시간, 총 거리가 포함되어
	 * 있다.
	 * <p>
	 * When 지하철 노선 최단 거리 검색을 한다. And  최단 거리 경로가 여러 개 존재한다. Then 최단 거리 경로를 하나만 응답한다.
	 */
	@Test
	void searchPath() {
		Map<String, StationResponse> stationAll = createStationAll();
		Map<String, LineResponse> lineAll = createLineAll();
		addLineStation(lineAll, stationAll);

	}

	private Map<String, StationResponse> createStationAll() {
		Map<String, StationResponse> stations = new HashMap<>();

		stations.put("서초", createStation("서초"));
		stations.put("교대", createStation("교대"));
		stations.put("강남", createStation("강남"));
		stations.put("역삼", createStation("역삼"));
		stations.put("선릉", createStation("선릉"));
		stations.put("삼성", createStation("삼성"));
		stations.put("남부터미널", createStation("남부터미널"));
		stations.put("양재", createStation("양재"));
		stations.put("양재시민의숲", createStation("양재시민의숲"));
		stations.put("고속터미널", createStation("고속터미널"));
		stations.put("사평", createStation("사평"));
		stations.put("신논현", createStation("신논현"));
		stations.put("언주", createStation("언주"));
		stations.put("선정릉", createStation("선정릉"));
		stations.put("삼성중앙", createStation("삼성중앙"));
		stations.put("봉은사", createStation("봉은사"));
		stations.put("종합운동장", createStation("종합운동장"));

		return stations;
	}

	private Map<String, LineResponse> createLineAll() {
		Map<String, LineResponse> lines = new HashMap<>();
		lines.put("2호선", createLine("2호선"));
		lines.put("3호선", createLine("3호선"));
		lines.put("9호선", createLine("9호선"));
		lines.put("신분당선", createLine("신분당선"));
		return lines;
	}

	private void addLineStation(Map<String, LineResponse> lineAll,
		Map<String, StationResponse> stationAll) {
		addLineStation(lineAll.get("2호선"), null, stationAll.get("서초"), 2, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("서초"), stationAll.get("교대"), 2, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("교대"), stationAll.get("강남"), 3, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("강남"), stationAll.get("역삼"), 3, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("역삼"), stationAll.get("선릉"), 3, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("선릉"), stationAll.get("삼성"), 3, 1);
		addLineStation(lineAll.get("2호선"), stationAll.get("삼성"), stationAll.get("종합운동장"), 4, 1);

		addLineStation(lineAll.get("3호선"), null, stationAll.get("고속터미널"), 2, 1);
		addLineStation(lineAll.get("3호선"), stationAll.get("고속터미널"), stationAll.get("교대"), 2, 1);
		addLineStation(lineAll.get("3호선"), stationAll.get("교대"), stationAll.get("남부터미널"), 2, 1);
		addLineStation(lineAll.get("3호선"), stationAll.get("남부터미널"), stationAll.get("양재"), 2, 1);

		addLineStation(lineAll.get("9호선"), null, stationAll.get("고속터미널"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("고속터미널"), stationAll.get("사평"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("사평"), stationAll.get("신논현"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("신논현"), stationAll.get("언주"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("언주"), stationAll.get("선정릉"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("선정릉"), stationAll.get("삼성중앙"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("삼성중앙"), stationAll.get("봉은사"), 2, 1);
		addLineStation(lineAll.get("9호선"), stationAll.get("봉은사"), stationAll.get("종합운동장"), 2, 1);

		addLineStation(lineAll.get("신분당선"), null, stationAll.get("강남"), 2, 1);
		addLineStation(lineAll.get("신분당선"), stationAll.get("강남"), stationAll.get("양재"), 2, 1);
		addLineStation(lineAll.get("신분당선"), stationAll.get("양재"), stationAll.get("양재시민의숲"), 2, 1);
	}

	void addLineStation(LineResponse line, StationResponse preStation, StationResponse station,
		Integer distance, Integer duration) {
		if (preStation == null) {
			addLineStation(line.getId(), null, station.getId(), distance, duration);
			return;
		}
		addLineStation(line.getId(), preStation.getId(), station.getId(), distance, duration);
	}
}
