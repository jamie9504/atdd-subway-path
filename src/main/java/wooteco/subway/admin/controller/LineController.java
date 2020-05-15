package wooteco.subway.admin.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wooteco.subway.admin.dto.LineRequest;
import wooteco.subway.admin.dto.LineResponse;
import wooteco.subway.admin.dto.LineStationCreateRequest;
import wooteco.subway.admin.dto.WholeSubwayResponse;
import wooteco.subway.admin.service.LineService;

@RestController
@RequestMapping("/api/lines")
public class LineController {

	private final LineService lineService;

	public LineController(LineService lineService) {
		this.lineService = lineService;
	}

	@GetMapping
	public ResponseEntity<List<LineResponse>> getLines() {
		List<LineResponse> lineResponses = lineService.showLines();
		return new ResponseEntity<>(lineResponses, HttpStatus.OK);
	}

	@GetMapping("/stations")
	public ResponseEntity<List<LineResponse>> findLinesWithStations() {
		List<LineResponse> lineResponses = lineService.showLinesWithStations();
		return new ResponseEntity<>(lineResponses, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LineResponse> createLine(@RequestBody @Valid LineRequest request) {
		LineResponse lineResponse = lineService.save(request.toLine());
		return new ResponseEntity<>(lineResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LineResponse> findById(@PathVariable Long id) {
		LineResponse lineResponse = lineService.findLineResponseById(id);
		return new ResponseEntity<>(lineResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LineResponse> updateLines(@PathVariable Long id,
		@RequestBody @Valid LineRequest request) {
		LineResponse lineResponse = lineService.updateLine(id, request.toLine());
		return new ResponseEntity<>(lineResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		lineService.deleteLineById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("{lineId}/stations")
	public ResponseEntity<Void> addStationToLine(@PathVariable Long lineId,
		@RequestBody @Valid LineStationCreateRequest request) {
		lineService.addLineStation(lineId, request);
		return ResponseEntity.created(URI.create("/admin-edge")).build();
	}

	@GetMapping("{lineId}/stations")
	public ResponseEntity<LineResponse> findStationsByLineId(@PathVariable Long lineId) {
		LineResponse lineResponse = lineService.findLineResponseWithStationsById(lineId);
		return new ResponseEntity<>(lineResponse, HttpStatus.OK);
	}

	@DeleteMapping("{lineId}/stations/{stationsId}")
	public ResponseEntity<Void> deleteStationByLineId(@PathVariable Long lineId,
		@PathVariable Long stationsId) {
		lineService.removeLineStation(lineId, stationsId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/detail")
	public ResponseEntity<WholeSubwayResponse> wholeLines() {
		WholeSubwayResponse response = lineService.wholeLines();

		return ResponseEntity
			.ok()
			.body(response);
	}
}