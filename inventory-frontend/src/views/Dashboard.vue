<template>
  <div>
    <h1>{{ $t("dashboard") }}</h1>
    <div class="row">
      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (bar)`" class="shadow-sm">
          <apexchart
            type="bar"
            :options="chartOptions1"
            :series="series1"
          ></apexchart>
        </b-card>
      </div>
      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (bar)`" class="shadow-sm">
          <apexchart
            type="bar"
            :options="chartOptions3"
            :series="series3"
          ></apexchart>
        </b-card>
      </div>

      <div class="col-md-6 my-2">
        <b-card :header="`Dummy Chart (pie)`" class="shadow-sm">
          <apexchart :options="chartOptions2" :series="series2"></apexchart>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      chartOptions1: {
        chart: {
          id: "vuechart-example1",
        },
        xaxis: {
          categories: [],
        },
      },
      series1: [
        {
          name: "已盤點",
          data: [],
        },
        {
          name: "未盤點",
          data: [],
        },
      ],
      series2: [],
      chartOptions3: {
        chart: {
          id: "vuechart-example3",
        },
        xaxis: {
          categories: [],
        },
      },
      series3: [
        {
          name: "盤點數目",
          data: [],
        },
      ],
    };
  },
  computed: {
    chartOptions2() {
      return {
        chart: {
          width: "100%",
          type: "pie",
        },
        labels: [],
        theme: {
          monochrome: {
            enabled: false,
          },
        },
        plotOptions: {
          pie: {
            dataLabels: {
              offset: -5,
            },
          },
        },
        dataLabels: {
          formatter(val, opts) {
            const name = opts.w.globals.labels[opts.seriesIndex];
            return [name, val.toFixed(1) + "%"];
          },
        },
        legend: {
          show: false,
        },
      };
    },
  },
  methods: {
    loadDashboardData() {
      this.$api.inventoryApi
        .getDashboardProgress()
        .then((p) => this.loadDashboardProgress(p))
        .catch((e) => console.error(e));
      this.$api.inventoryApi
        .getDashboardMasterCount()
        .then((p) => this.loadDashboardMasterCount(p))
        .catch((e) => console.error(e));
      this.$api.inventoryApi
        .getDashBoardDetailCount()
        .then((p) => this.loadDashBoardDetailCount(p))
        .catch((e) => console.error(e));
    },
    loadDashboardMasterCount(data) {
      let records = data.counter;
      for (let i = 0; i < records.length; i++) {
        this.series2[i] = records[i].count;
        this.chartOptions2.labels[i] = records[i].deptName;
      }
    },
    loadDashboardProgress(data) {
      let records = data.progress;
      for (let i = 0; i < records.length; i++) {
        this.series1[0].data[i] = records[i].finishedCount;
        this.series1[1].data[i] = records[i].nonFinishCount;
        this.chartOptions1.xaxis.categories[i] = records[i].masterTitle;
      }
    },
    loadDashBoardDetailCount(data) {
      let records = data.detailCount;
      for (let i = 0; i < records.length; i++) {
        this.series3[0].data[i] = records[i].detailCount;
        this.chartOptions3.xaxis.categories[i] = records[i].masterTitle;
      }
    },
  },
  mounted() {
    this.loadDashboardData();
  },
};
</script>