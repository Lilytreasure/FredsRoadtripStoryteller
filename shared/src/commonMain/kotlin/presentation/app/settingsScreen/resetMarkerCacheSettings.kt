package presentation.app.settingsScreen

import data.AppSettings
import data.AppSettings.Companion.kMarkersLastUpdatedLocation
import data.AppSettings.Companion.kMarkersResult
import data.AppSettings.Companion.kRecentlySeenMarkersSet
import data.AppSettings.Companion.kUiRecentlySeenMarkersList
import data.MarkersRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import presentation.maps.Marker
import presentation.maps.RecentlySeenMarker

fun resetMarkerCacheSettings(
    settings: AppSettings,
    finalMarkers: MutableStateFlow<List<Marker>>,
    recentlySeenMarkersSet: MutableStateFlow<Set<RecentlySeenMarker>>,
    uiRecentlySeenMarkersFlow: MutableStateFlow<List<RecentlySeenMarker>>,
    markersRepo: MarkersRepo,
) {
    // Reset the `seen markers` list, UI elements
    finalMarkers.update { emptyList() }
    recentlySeenMarkersSet.update { emptySet() }
    uiRecentlySeenMarkersFlow.update { emptyList() }

    // Reset the settings cache of markers
    settings.clear(kMarkersResult)
    settings.clear(kMarkersLastUpdatedLocation)
    settings.clear(kRecentlySeenMarkersSet)
    settings.clear(kUiRecentlySeenMarkersList)
    markersRepo.clearAllMarkers()
}
