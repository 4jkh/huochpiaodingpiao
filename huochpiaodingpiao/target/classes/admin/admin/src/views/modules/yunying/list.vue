<template>
    <div class="main-content">
        <div id="chart-container" style="width: 1200px; height: 800px;"></div>
    </div>
</template>
<script>
import echarts from 'echarts';

export default {
    data() {
        return {
            searchForm: {
                key: ""
            },
            sessionTable: "",//登录账户所在表名
            role: "",//权限
            userId: "",//当前登录人的id
            dataList: [],
            showFlag: true,
            sfshVisiable: false,
            chartVisiable: false,
            echartsDate: new Date(),//echarts的时间查询字段
            addOrUpdateFlag: false,
        };
    },
    created() {
    },
    mounted() {
        this.initChart();
    },
    computed: {
    },
    methods: {
        initChart() {
            this.$http({
                url: "checiOrder/echart",
                method: "get",
            }).then(({ data }) => {
                if (data && data.code === 0) {
                    this.renderBarChart(data.data.categories, data.data.values1, data.data.values2);
                }
            });

        },
        renderBarChart(categories, values1, values2) {
            const chartDom = document.getElementById('chart-container');
            const myChart = echarts.init(chartDom);
            const option = {
                // 添加 tooltip 选项
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['销售量', '销售额']
                },
                xAxis: {
                    type: 'category',
                    data: categories
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '销售量',
                        data: values1,
                        type: 'bar'
                    },
                    {
                        name: '销售额',
                        data: values2,
                        type: 'bar'
                    }
                ]
            };
            myChart.setOption(option);
        }
    }
};
</script>
<style lang="scss" scoped>
.slt {
    margin: 0 !important;
    display: flex;
}

.ad {
    margin: 0 !important;
    display: flex;
}

.pages {
    & /deep/ el-pagination__sizes {
        & /deep/ el-input__inner {
            height: 22px;
            line-height: 22px;
        }
    }
}


.el-button+.el-button {
    margin: 0;
}

.tables {
    & /deep/ .el-button--success {
        height: 36px;
        color: rgba(40, 167, 69, 1);
        font-size: 10px;
        border-width: 0px;
        border-style: solid;
        border-color: #DCDFE6;
        border-radius: 0px;
        background-color: rgba(255, 255, 255, 1);
    }

    & /deep/ .el-button--primary {
        height: 36px;
        color: rgba(255, 193, 7, 1);
        font-size: 10px;
        border-width: 0px;
        border-style: solid;
        border-color: #DCDFE6;
        border-radius: 0px;
        background-color: #fff;
    }

    & /deep/ .el-button--danger {
        height: 36px;
        color: rgba(220, 53, 69, 1);
        font-size: 10px;
        border-width: 0px;
        border-style: solid;
        border-color: #DCDFE6;
        border-radius: 0px;
        background-color: #fff;
    }

    & /deep/ .el-button {
        margin: 4px;
    }
}
</style>
