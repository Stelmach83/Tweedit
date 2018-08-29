<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<div class="card">
    <div class="card-body" style="background-color: #f4f4f4;">
        <div class="row">
            <div class="col-sm-5">
                <h4 class="card-title mb-0">Your wall</h4>
                <div class="small text-muted"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}"/></div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-check"></i>Card with label
                        <span class="badge badge-pill badge-warning float-right">42</span>
                    </div>
                    <div class="card-body">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-check"></i>Card with label
                        <span class="badge badge-pill badge-warning float-right">42</span>
                    </div>
                    <div class="card-body">
                        Purus non enim praesent elementum facilisis leo. Quis varius quam quisque id diam vel quam elementum pulvinar. Urna condimentum mattis pellentesque id nibh tortor. Eget dolor morbi non arcu risus quis. Pulvinar sapien et ligula ullamcorper. Nulla facilisi cras fermentum odio eu feugiat. Id aliquet lectus proin nibh nisl condimentum id. Eget aliquet nibh praesent tristique magna sit amet purus. Netus et malesuada fames ac turpis. Facilisis gravida neque convallis a. Fringilla urna porttitor rhoncus dolor purus. Parturient montes nascetur ridiculus mus mauris. Nisi lacus sed viverra tellus in hac habitasse platea. Donec pretium vulputate sapien nec. Nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper sit. Risus quis varius quam quisque id diam vel. Id diam maecenas ultricies mi eget mauris pharetra. Convallis posuere morbi leo urna molestie at elementum eu facilisis. Tellus mauris a diam maecenas sed enim. Ornare suspendisse sed nisi lacus sed.
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-check"></i>Card with label
                        <span class="badge badge-pill badge-warning float-right">42</span>
                    </div>
                    <div class="card-body">
                        Nunc aliquet bibendum enim facilisis gravida neque convallis a cras. Quis viverra nibh cras pulvinar. Sed risus pretium quam vulputate dignissim suspendisse in est ante. Eu nisl nunc mi ipsum faucibus vitae. Semper auctor neque vitae tempus quam. Sagittis vitae et leo duis. Risus commodo viverra maecenas accumsan lacus vel facilisis volutpat est. Amet nulla facilisi morbi tempus iaculis urna id. Amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar sapien. Tempus egestas sed sed risus pretium quam vulputate dignissim suspendisse. Natoque penatibus et magnis dis parturient montes. Ut enim blandit volutpat maecenas volutpat blandit.
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="card">
                    <div class="card-header">
                        <i class="fa fa-check"></i>Card with label
                        <span class="badge badge-pill badge-warning float-right">42</span>
                    </div>
                    <div class="card-body">
                        Massa tincidunt nunc pulvinar sapien et. Nunc sed id semper risus in hendrerit gravida rutrum. Iaculis nunc sed augue lacus viverra vitae congue eu consequat. Vulputate enim nulla aliquet porttitor lacus luctus accumsan. Massa tempor nec feugiat nisl pretium fusce id velit. Elit ullamcorper dignissim cras tincidunt. Nisl pretium fusce id velit. Nibh cras pulvinar mattis nunc sed blandit libero volutpat sed. At urna condimentum mattis pellentesque. Neque ornare aenean euismod elementum nisi quis eleifend. Donec massa sapien faucibus et molestie ac feugiat sed. Malesuada nunc vel risus commodo. Turpis tincidunt id aliquet risus feugiat in ante metus dictum. In iaculis nunc sed augue lacus viverra vitae. Elit pellentesque habitant morbi tristique. Eget magna fermentum iaculis eu non diam phasellus. Facilisis leo vel fringilla est.
                    </div>
                </div>
            </div>
        </div>

        <div class="chart-wrapper" style="height:300px;margin-top:40px;">
            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
                </div>
                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                    <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
                </div>
            </div>
            <canvas id="main-chart" class="chart chartjs-render-monitor" height="600" width="3204" style="display: block; height: 300px; width: 1602px;"></canvas>
            <div id="main-chart-tooltip" class="chartjs-tooltip center" style="opacity: 0; left: 114.016px; top: 181.48px;">
                <div class="tooltip-header">
                    <div class="tooltip-header-item">T</div>
                </div>
                <div class="tooltip-body">
                    <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My First dataset</span><span
                            class="tooltip-body-item-value">180</span></div>
                    <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My Second dataset</span><span
                            class="tooltip-body-item-value">97</span></div>
                    <div class="tooltip-body-item"><span class="tooltip-body-item-color" style="background-color: rgb(255, 255, 255);"></span><span class="tooltip-body-item-label">My Third dataset</span><span
                            class="tooltip-body-item-value">65</span></div>
                </div>
            </div>
        </div>
    </div>
    <div class="card-footer">
        <div class="row text-center">
            <div class="col-sm-12 col-md mb-sm-2 mb-0">
                <div class="text-muted">Visits</div>
                <strong>29.703 Users (40%)</strong>
                <div class="progress progress-xs mt-2">
                    <div class="progress-bar bg-success" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
            <div class="col-sm-12 col-md mb-sm-2 mb-0">
                <div class="text-muted">Unique</div>
                <strong>24.093 Users (20%)</strong>
                <div class="progress progress-xs mt-2">
                    <div class="progress-bar bg-info" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
            <div class="col-sm-12 col-md mb-sm-2 mb-0">
                <div class="text-muted">Pageviews</div>
                <strong>78.706 Views (60%)</strong>
                <div class="progress progress-xs mt-2">
                    <div class="progress-bar bg-warning" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
            <div class="col-sm-12 col-md mb-sm-2 mb-0">
                <div class="text-muted">New Users</div>
                <strong>22.123 Users (80%)</strong>
                <div class="progress progress-xs mt-2">
                    <div class="progress-bar bg-danger" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
            <div class="col-sm-12 col-md mb-sm-2 mb-0">
                <div class="text-muted">Bounce Rate</div>
                <strong>40.15%</strong>
                <div class="progress progress-xs mt-2">
                    <div class="progress-bar" role="progressbar" style="width: 40%" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
            </div>
        </div>
    </div>
</div>