$(function () {
    //异步加载示例代码，如页面不刷新，则仅仅加载一次
    $('#sdkandqa').click(function(){
        var show = $(this).attr('show');
        var did = $(this).attr('did');
        if(show == 0) {
            $(this).attr('show','1');
            layer.load(1,{shade:0.3})
            $.getJSON("https://code.juhe.cn/api/docsByDid?callback=?", {
                "did" : did
            }, function(data) {
                layer.closeAll()
                var code = data.code;
                if(code == '1'){
                    var list = data.list;
                    var codehtml = '<tr class="title"><td width="40">&nbsp;</td><td width="100">语言</td><td>标题</td><td>提供者</td><td>时间</td></tr>';
                    for(var j in list){
                        codehtml +='<tr>'+
                            '<td >&nbsp;</td>'+
                            '<td class="url">'+list[j].language+'</td>'+
                            '<td ><a href="'+list[j].url+'" class="blue" target="_blank">'+list[j].title+'</a></td>'+
                            '<td >'+list[j].nickname+'</td>'+
                            '<td >'+list[j].addtime+'</td>'+
                            '</tr>';
                    }
                    $("#code_table").html(codehtml);
                }else{
                    var codehtml = '<tr class="title"><td width="40">&nbsp;</td><td width="100">语言</td><td>标题</td><td>提供者</td><td>时间</td></tr>';
                    codehtml +="<tr><td colspan='5' align='center'>暂无教学代码，小聚正在拼命添加中....</td></tr>";
                    $("#code_table").html(codehtml);
                }
            });
        }
    })

    $('#docs-api-area').on('click','ul.api-sub-list .api-aid',function(){

        $('ul.api-sub-list .api-aid').removeClass('active');
        $('ul.api-sub-list .api-aid a').removeClass('selected');

        $(this).addClass('active');
        $(this).children().addClass('selected');
        $.ajax({
            url:$(this).children().attr('href'),
            type:'post',
            data:'_token='+_token,
            dataType:'json',
            success:function(obj){
                if(obj.code == 0) {
                    $('div#docs-api-area').html(obj.result.html);
                }
            }

        })
    })
});

//套餐
$('.free-ul li').each(function(){
    $(this).click(function(){
        $('.price-ul li').removeClass('active');
        $(this).addClass('active');
    });
});

//pay
if($('.api-infos ul').hasClass('pay-ul')){

    if( $('.now-btn').hasClass('pay-buy-a1') ){
        var databuy = $('.pay-buy-a1').attr('href').slice(0,-3);
    }

    $('.pay-ul li').each(function(index){

        var price = $(this).attr('data-price');
        var num = $(this).attr('data-renums');
        var onceprice = (price/num);
        if(onceprice > 0) {
            onceprice = onceprice.toFixed(4);
        } else {
            onceprice = 0;
        }
        var remark = $(this).attr('data-remark');
        var dataDid = $(this).attr('data-did');
        if(index == 0) {
            $('.last-price .red').html('¥'+price);
            $('.last-price span em').html(onceprice);
        }
        $(this).click(function(){
            $('.price-ul li').removeClass('active');
            $(this).addClass('active');
            $('.last-price .red').html('¥'+price);
            $('.last-price span em').html(onceprice);

            if(remark){
                $('.last-price span i').html(', '+remark);
            } else {
                $('.last-price span i').html('');
            }

            if( $('.now-btn').hasClass('pay-buy-a1') ){
                $('.pay-buy-a1').attr('href',databuy+dataDid);
            }

        });

    });

    $('.pay-ul li:first').click();


}

//tab-pos
$(window).scroll(function(){
    var hhh = $(window).scrollTop();
    if ( hhh > 415){
        $(".tab-pos1").addClass('tab-pos');
        $('.docs-api-tab .api-now').show();
    } else {
        $(".tab-pos1").removeClass('tab-pos');
        $('.docs-api-tab .api-now').hide();
    };
});


//选项卡
function tabs(tabTit,on,tabCon){
    $(tabCon).each(function(){
        if($(tabTit+' li').hasClass('move-api-set') && $('#realAid').val()!=0) {
            $(this).children().eq(1).show();
        } else {
            $(this).children().eq(0).show();
        }
    });
    $(tabTit).each(function(){
        if($(tabTit+' li').hasClass('move-api-set') && $('#realAid').val()!=0) {
            $(this).children().eq(1).addClass(on);
            $(this).children().eq(0).removeClass(on);
        } else {
            $(this).children().eq(0).addClass(on);
        }
    });
    $(tabTit).children().click(function(){
        $(this).addClass(on).siblings().removeClass(on);
        var index = $(tabTit).children().index(this);
        $(tabCon).children().eq(index).show().siblings().hide();
    });
}
tabs(".tabul", "active", ".tabDiv");
$('.apides').each(function(){
    $(this).mouseover(function(){
        $(this).find('.apidescipe').show();
    }).mouseout(function(){
        $(this).find('.apidescipe').hide();
    })
})

var canvas = document.getElementById('canvas'),
    ctx = canvas.getContext('2d'),
    canvas_width = canvas.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
    canvas_height = canvas.height = 230,
    random_points = [];
function draw() {
    ctx.clearRect(0, 0, canvas_width, canvas_height);
    var i, pi, x_dist, y_dist, dist, w;
    random_points.forEach(function (p, index) {
        p.x += p.xa;
        p.y += p.ya;
        p.xa *= p.x > canvas_width || p.x < 0 ? -1 : 1;
        p.ya *= p.y > canvas_height || p.y < 0 ? -1 : 1;
        ctx.fillRect(p.x - 0.5, p.y - 0.5, 4, 4);
        ctx.fillStyle = '#cacaca';
        for (i = index + 1; i < random_points.length; i++) {
            pi = random_points[i];
            if (pi.x !== null && pi.y !== null) {
                x_dist = p.x - pi.x;
                y_dist = p.y - pi.y;
                dist = x_dist * x_dist + y_dist * y_dist;
                w = (pi.max - dist) / pi.max + 0.2;
                ctx.beginPath();
                ctx.lineWidth = w;
                ctx.strokeStyle = 'rgba(180,180,180,' + w + ')';
                ctx.moveTo(p.x, p.y);
                ctx.lineTo(pi.x, pi.y);
                ctx.stroke();
            }
        }
    });
    requestAnimationFrame(draw);
}
for (var i = 0; i < 50; i++) {
    var x = Math.random() * canvas_width,
        y = Math.random() * canvas_height,
        xa = .8 * Math.random() - .4,
        ya = .8 * Math.random() - .4,
        max = 10000;
    random_points[i] = { x: x, y: y, xa: xa, ya: ya, max: max };
}
setTimeout(draw, 100);
