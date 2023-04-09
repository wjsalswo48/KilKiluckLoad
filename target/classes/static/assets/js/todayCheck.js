    let $todayCheck = $("#todayCheck");

    $(function a() {
        $.ajax({
            type: "GET",
            url: "/todayCheckALL",
            data: {
                "memberID": $("#principalUsername").html()
            },
            success: function (data) {
                if (data = 1) {
                    $("#todayCheck").prop('disabled', true);
                    $("#todayCheck").html('출석완료');
                }
            }

        })
        console.log(' 출석정보 불러오기');
    });

    function b() {
        $.ajax({
            type: "GET",
            url: "/todayCheck",
            data: {
                "memberID": $("#principalUsername").html()
            },
            success: function (data) {
                console.log(data);
                alert("출석체크 완료! 100포인트가 지급되었습니다!");
                console.log(data);
                $("#totalPoint").text(data);
                $("#todayCheck").prop('disabled', true);
                $("#todayCheck").html('출석완료');
            },
        })
    }