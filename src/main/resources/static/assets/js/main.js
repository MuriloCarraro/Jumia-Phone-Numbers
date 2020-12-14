(function($) {

	skel.breakpoints({
		xlarge:		'(max-width: 1680px)',
		large:		'(max-width: 1280px)',
		medium:		'(max-width: 980px)',
		small:		'(max-width: 736px)',
		xsmall:		'(max-width: 480px)',
		xxsmall:	'(max-width: 360px)'
	});

	$(function() {

		var	$window = $(window),
		$body = $('body'),
		$header = $('#header'),
		$footer = $('#footer'),
		$main = $('#main'),
		$article = $('#article');
		$paglist = $('#pag-list');

		var country = null, state = null, page = 0, pageBlock = 1, pageMax = 10;
		var paginationArrow =  '<svg xmlns="http://www.w3.org/2000/svg" width="8" height="12" viewbox="0 0 8 12"><g fill="none" fill-rule="evenodd"><path fill="#ffffff" d="M7.41 1.41L6 0 0 6l6 6 1.41-1.41L2.83 6z"></path></g></svg>'

		$main.getData = function() {

			let query = '';
			if ((country != null && country != 'All') || (state != null && state != 'All')){
				query += '?'; 
				if (country != null && country != 'All'){
					query += 'countryCode=' + country; 
					if (state != null && state != 'All' ){
						query += '&state=' + state;
					}
				}else {
					query += 'state=' + state; 
				}
				query += '&page=' + page; 
			}else{
				query += '?page=' + page; 
			}

			fetch( location.origin + "/api/phone" + query)
			.then(function (response) {
				return response.json();
			})
			.then(function (response) {

				if(page >= response.totalPages && response.totalPages > 0){
					page = response.totalPages - 1;
					if(page <= (1 + getMinPage())){
						pageBlock = Math.floor(page / pageMax) + 1
					}
					$main.getData();
				}

				if (response.data.length == 0){
					document.querySelector('#table tbody').innerHTML = '';
					document.querySelector('#table tfoot').innerHTML = '<tr><td>No Data</td><td colspan="3"></td></tr>';
				}else{
					document.querySelector('#table tfoot').innerHTML = '';
					let tr = '';
					response.data.forEach(function(element) {
						tr += `<tr><td>${element.countryName}</td><td>${element.countryCode != '-' ? ( '+' + element.countryCode ) : '-'}</td><td>${element.phoneNumber}</td><td>${element.state == 'VALID' ? 'Valid' : 'Not Valid'}</td></tr>`;
					});	
					document.querySelector('#table tbody').innerHTML = tr;
				}

				$paglist.html("");

				if(response.totalPages > 1){

					let maxPagesHit = false;
					let maxPage = response.totalPages;
					let minPage = 1 + getMinPage();

					if (response.totalPages > pageMax * pageBlock){
						maxPagesHit = true;
						maxPage = pageMax * pageBlock;
					}

					if(page > 0){
						$paglist.append(`<li class="pag-item pag-item-prev"> <a class="pag-link" onclick="onPagePrev();return false;">${paginationArrow}</a></li>`);
					}
					
					if(pageBlock > 1){
						$paglist.append('<li class="pag-item"><a class="pag-link" onclick="onPaginationMore(\'backward\');return false;">...</a></li>');
					}

					for (i = minPage; i <= maxPage; i++) {
						if(i == response.page +1){
							$paglist.append(`<li class="pag-item active"><a class="pag-link" onclick="onPaginationClick(${i});return false;">${i}</a></li>`);
						}else{
							$paglist.append(`<li class="pag-item"><a class="pag-link" onclick="onPaginationClick(${i});return false;">${i}</a></li>`);
						}
					}

					if(maxPagesHit){
						$paglist.append('<li class="pag-item"><a class="pag-link" onclick="onPaginationMore(\'forward\');return false;">...</a></li>');
					}

					if(page +1 < response.totalPages){
						$paglist.append(`<li class="pag-item pag-item-next"> <a class="pag-link" onclick="onPageNext();return false;">${paginationArrow}</a></li>`);
					}
				}
			})
			.catch(function (error) {
				console.log("Error: " + error);
			});
		}

		getMinPage = function() {
			return (pageBlock > 1 ? pageMax * (pageBlock -1) : 0);
		}

		onPaginationClick = function(value) {
			if(value != page + 1){
				page = value - 1;
				$main.getData();
			}
		}

		onPagePrev = function() {
			page --;
			if(page < pageMax * (pageBlock -1)){
				pageBlock --;
			}
			$main.getData();
		}

		onPageNext = function() {
			page ++;
			if(page >= pageMax * pageBlock){
				pageBlock ++;
			}
			$main.getData();
		}
		

		onPaginationMore = function(direction) {
			if(direction == 'forward'){
				pageBlock ++;
				page = pageMax * (pageBlock -1);
				$main.getData();
			}else if (direction == 'backward' && pageBlock > 1){
				pageBlock --;
				page = getMinPage();
				$main.getData();
			}
		}

		$('#country-category').on('change', function() {
			if(this.value != country){
				country = this.value;
				$main.getData();
			}
		});

		$('#state-category').on('change', function() {
			if(this.value != state){
				state = this.value;
				$main.getData();
			}
		});

		$('#myLink').click(function(){ MyFunction(); return false; });

		$body.addClass('is-loading');

		$main.hide();
		$footer.hide();

		$window.on('load', function() {
			window.setTimeout(function() {
				$body.removeClass('is-loading');
			}, 100);

			window.setTimeout(function() {
				$main.show();
				$article.show();
				$article.addClass('active');
				$header.hide();
				$footer.show();
			}, 3000);
		});

		$main.getData();
	});
})(jQuery);