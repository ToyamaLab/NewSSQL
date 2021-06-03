// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2017 Barend Gehrels, Amsterdam, the Netherlands.
<<<<<<< HEAD
=======
// Copyright (c) 2017 Adam Wulkiewicz, Lodz, Poland.

// This file was modified by Oracle on 2019.
// Modifications copyright (c) 2019 Oracle and/or its affiliates.

// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_OVERLAY_HANDLE_SELF_TURNS_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_OVERLAY_HANDLE_SELF_TURNS_HPP

#include <boost/range.hpp>

#include <boost/geometry/algorithms/detail/overlay/cluster_info.hpp>
#include <boost/geometry/algorithms/detail/overlay/is_self_turn.hpp>
#include <boost/geometry/algorithms/detail/overlay/overlay_type.hpp>
<<<<<<< HEAD
=======
#include <boost/geometry/algorithms/covered_by.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/geometry/algorithms/within.hpp>

namespace boost { namespace geometry
{

#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace overlay
{

<<<<<<< HEAD
struct discard_turns
{
    template <typename Turns, typename Clusters, typename Geometry0, typename Geometry1>
    static inline
    void apply(Turns& , Clusters const& , Geometry0 const& , Geometry1 const& )
=======

template
<
    typename Point, typename Geometry,
    typename Tag2 = typename geometry::tag<Geometry>::type
>
struct check_within_strategy
{
    template <typename Strategy>
    static inline typename Strategy::template point_in_geometry_strategy<Point, Geometry>::type
        within(Strategy const& strategy)
    {
        return strategy.template get_point_in_geometry_strategy<Point, Geometry>();
    }

    template <typename Strategy>
    static inline typename Strategy::template point_in_geometry_strategy<Point, Geometry>::type
        covered_by(Strategy const& strategy)
    {
        return strategy.template get_point_in_geometry_strategy<Point, Geometry>();
    }
};

template <typename Point, typename Geometry>
struct check_within_strategy<Point, Geometry, box_tag>
{
    template <typename Strategy>
    static inline typename Strategy::within_point_box_strategy_type
        within(Strategy const& )
    {
        return typename Strategy::within_point_box_strategy_type();
    }

    template <typename Strategy>
    static inline typename Strategy::covered_by_point_box_strategy_type
        covered_by(Strategy const&)
    {
        return typename Strategy::covered_by_point_box_strategy_type();
    }
};


template <overlay_type OverlayType>
struct check_within
{
    template
    <
        typename Turn, typename Geometry0, typename Geometry1,
        typename UmbrellaStrategy
    >
    static inline
    bool apply(Turn const& turn, Geometry0 const& geometry0,
               Geometry1 const& geometry1, UmbrellaStrategy const& strategy)
    {
        typedef typename Turn::point_type point_type;

        // Operations 0 and 1 have the same source index in self-turns
        return turn.operations[0].seg_id.source_index == 0
            ? geometry::within(turn.point, geometry1,
                check_within_strategy<point_type, Geometry1>::within(strategy))
            : geometry::within(turn.point, geometry0,
                check_within_strategy<point_type, Geometry0>::within(strategy));
    }

};

template <>
struct check_within<overlay_difference>
{
    template
    <
        typename Turn, typename Geometry0, typename Geometry1,
        typename UmbrellaStrategy
    >
    static inline
    bool apply(Turn const& turn, Geometry0 const& geometry0,
               Geometry1 const& geometry1, UmbrellaStrategy const& strategy)
    {
        typedef typename Turn::point_type point_type;

        // difference = intersection(a, reverse(b))
        // therefore we should reverse the meaning of within for geometry1
        return turn.operations[0].seg_id.source_index == 0
            ? ! geometry::covered_by(turn.point, geometry1,
                  check_within_strategy<point_type, Geometry1>::covered_by(strategy))
            : geometry::within(turn.point, geometry0,
                check_within_strategy<point_type, Geometry0>::within(strategy));
    }
};

struct discard_turns
{
    template
    <
        typename Turns, typename Clusters,
        typename Geometry0, typename Geometry1,
        typename Strategy
    >
    static inline
    void apply(Turns& , Clusters const& ,
               Geometry0 const& , Geometry1 const& ,
               Strategy const& )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}
};

template <overlay_type OverlayType, operation_type OperationType>
struct discard_closed_turns : discard_turns {};

// It is only implemented for operation_union, not in buffer
template <>
struct discard_closed_turns<overlay_union, operation_union>
{
<<<<<<< HEAD

    template <typename Turns, typename Clusters, typename Geometry0, typename Geometry1>
    static inline
    void apply(Turns& turns, Clusters const& clusters,
            Geometry0 const& geometry0, Geometry1 const& geometry1)
=======
    // Point in Geometry Strategy
    template
    <
        typename Turns, typename Clusters,
        typename Geometry0, typename Geometry1,
        typename Strategy
    >
    static inline
    void apply(Turns& turns, Clusters const& /*clusters*/,
               Geometry0 const& geometry0, Geometry1 const& geometry1,
               Strategy const& strategy)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        typedef typename boost::range_value<Turns>::type turn_type;

        for (typename boost::range_iterator<Turns>::type
                it = boost::begin(turns);
             it != boost::end(turns);
             ++it)
        {
            turn_type& turn = *it;

<<<<<<< HEAD
            if (turn.discarded || ! is_self_turn<overlay_union>(turn))
            {
                continue;
            }

            bool const within =
                    turn.operations[0].seg_id.source_index == 0
                    ? geometry::within(turn.point, geometry1)
                    : geometry::within(turn.point, geometry0);

            if (within)
            {
                // It is in the interior of the other geometry
=======
            if (! turn.discarded
                && is_self_turn<overlay_union>(turn)
                && check_within<overlay_union>::apply(turn, geometry0,
                                                      geometry1, strategy))
            {
                // Turn is in the interior of other geometry
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                turn.discarded = true;
            }
        }
    }
};

<<<<<<< HEAD
=======
template <overlay_type OverlayType>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct discard_self_intersection_turns
{
private :

    template <typename Turns, typename Clusters>
    static inline
<<<<<<< HEAD
    bool any_blocked(signed_size_type cluster_id,
            const Turns& turns, Clusters const& clusters)
    {
        typename Clusters::const_iterator cit = clusters.find(cluster_id);
        if (cit == clusters.end())
        {
            return false;
        }
        cluster_info const& cinfo = cit->second;
        for (std::set<signed_size_type>::const_iterator it
             = cinfo.turn_indices.begin();
             it != cinfo.turn_indices.end(); ++it)
        {
            typename boost::range_value<Turns>::type const& turn = turns[*it];
            if (turn.any_blocked())
            {
                return true;
            }
        }
        return false;
    }

    template <typename Turns, typename Clusters>
    static inline
=======
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    bool is_self_cluster(signed_size_type cluster_id,
            const Turns& turns, Clusters const& clusters)
    {
        typename Clusters::const_iterator cit = clusters.find(cluster_id);
        if (cit == clusters.end())
        {
            return false;
        }

        cluster_info const& cinfo = cit->second;
        for (std::set<signed_size_type>::const_iterator it
             = cinfo.turn_indices.begin();
             it != cinfo.turn_indices.end(); ++it)
        {
<<<<<<< HEAD
            if (! is_self_turn<overlay_intersection>(turns[*it]))
=======
            if (! is_self_turn<OverlayType>(turns[*it]))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                return false;
            }
        }

        return true;
    }

<<<<<<< HEAD
    template <typename Turn, typename Geometry0, typename Geometry1>
    static inline
    bool within(Turn const& turn, Geometry0 const& geometry0,
                Geometry1 const& geometry1)
    {
        return turn.operations[0].seg_id.source_index == 0
            ? geometry::within(turn.point, geometry1)
            : geometry::within(turn.point, geometry0);
    }

    template <typename Turns, typename Clusters,
              typename Geometry0, typename Geometry1>
    static inline
    void discard_clusters(Turns& turns, Clusters const& clusters,
            Geometry0 const& geometry0, Geometry1 const& geometry1)
=======
    template <typename Turns, typename Clusters,
              typename Geometry0, typename Geometry1, typename Strategy>
    static inline
    void discard_clusters(Turns& turns, Clusters const& clusters,
            Geometry0 const& geometry0, Geometry1 const& geometry1,
            Strategy const& strategy)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        for (typename Clusters::const_iterator cit = clusters.begin();
             cit != clusters.end(); ++cit)
        {
<<<<<<< HEAD
            signed_size_type cluster_id = cit->first;

            // If there are only self-turns in the cluster, the cluster should
            // be located within the other geometry, for intersection
            if (is_self_cluster(cluster_id, turns, clusters))
            {
                cluster_info const& cinfo = cit->second;
                if (! within(turns[*cinfo.turn_indices.begin()], geometry0, geometry1))
                {
                    // Discard all turns in cluster
                    for (std::set<signed_size_type>::const_iterator sit = cinfo.turn_indices.begin();
=======
            signed_size_type const cluster_id = cit->first;

            // If there are only self-turns in the cluster, the cluster should
            // be located within the other geometry, for intersection
            if (! cit->second.turn_indices.empty()
                && is_self_cluster(cluster_id, turns, clusters))
            {
                cluster_info const& cinfo = cit->second;
                signed_size_type const index = *cinfo.turn_indices.begin();
                if (! check_within<OverlayType>::apply(turns[index],
                                                       geometry0, geometry1,
                                                       strategy))
                {
                    // Discard all turns in cluster
                    for (std::set<signed_size_type>::const_iterator sit
                         = cinfo.turn_indices.begin();
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                         sit != cinfo.turn_indices.end(); ++sit)
                    {
                        turns[*sit].discarded = true;
                    }
                }
            }
        }
    }

public :

    template <typename Turns, typename Clusters,
<<<<<<< HEAD
              typename Geometry0, typename Geometry1>
    static inline
    void apply(Turns& turns, Clusters const& clusters,
            Geometry0 const& geometry0, Geometry1 const& geometry1)
    {
        discard_clusters(turns, clusters, geometry0, geometry1);
=======
              typename Geometry0, typename Geometry1, typename Strategy>
    static inline
    void apply(Turns& turns, Clusters const& clusters,
            Geometry0 const& geometry0, Geometry1 const& geometry1,
            Strategy const& strategy)
    {
        discard_clusters(turns, clusters, geometry0, geometry1, strategy);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        typedef typename boost::range_value<Turns>::type turn_type;

        for (typename boost::range_iterator<Turns>::type
                it = boost::begin(turns);
             it != boost::end(turns);
             ++it)
        {
            turn_type& turn = *it;

<<<<<<< HEAD
            if (turn.discarded || ! is_self_turn<overlay_intersection>(turn))
            {
                continue;
            }

            segment_identifier const& id0 = turn.operations[0].seg_id;
            segment_identifier const& id1 = turn.operations[1].seg_id;
            if (id0.multi_index != id1.multi_index
                    || (id0.ring_index == -1 && id1.ring_index == -1)
                    || (id0.ring_index >= 0 && id1.ring_index >= 0))
            {
                // Not an ii ring (int/ext) on same ring
                continue;
            }

            if (turn.is_clustered() && turn.has_colocated_both)
            {
                // Don't delete a self-ii-turn colocated with another ii-turn
                // (for example #case_recursive_boxes_70)
                // But for some cases (#case_58_iet) they should be deleted,
                // there are many self-turns there and also blocked turns there
                if (! any_blocked(turn.cluster_id, turns, clusters))
                {
                    continue;
                }
            }

            // It is a ii self-turn
            // Check if it is within the other geometry
            // If not, it can be ignored
            if (! within(turn, geometry0, geometry1))
            {
                // It is not within another geometry, discard the turn
                turn.discarded = true;
=======
            // It is a ii self-turn
            // Check if it is within the other geometry
            if (! turn.discarded
                && is_self_turn<overlay_intersection>(turn)
                && ! check_within<OverlayType>::apply(turn, geometry0,
                                                      geometry1, strategy))
            {
                // It is not within another geometry, set it as non startable.
                // It still might be traveled (#case_recursive_boxes_70)
                turn.operations[0].enriched.startable = false;
                turn.operations[1].enriched.startable = false;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }
    }
};

<<<<<<< HEAD
template <overlay_type OverlayType, operation_type OperationType>
struct discard_open_turns : discard_turns {};

// Handler it for intersection
template <>
struct discard_open_turns<overlay_intersection, operation_intersection>
        : discard_self_intersection_turns {};

// For difference, it should be done in a different way (TODO)


template <overlay_type OverlayType, typename Turns>
inline void discard_self_turns_which_loop(Turns& turns)
{
    if (operation_from_overlay<OverlayType>::value == operation_union)
    {
        // For union, self-turn i/u traveling to itself are allowed to form
        // holes. #case_recursive_boxes_37
        // TODO: this can be finetuned by inspecting the cluster too,
        // and if there are non-self-turns the polygons on their sides can
        // be checked
        return;
    }

    typedef typename boost::range_value<Turns>::type turn_type;
    typedef typename turn_type::turn_operation_type op_type;

    signed_size_type turn_index = 0;
    for (typename boost::range_iterator<Turns>::type
            it = boost::begin(turns);
         it != boost::end(turns);
         ++it, ++turn_index)
    {
        turn_type& turn = *it;

        if (! is_self_turn<OverlayType>(turn))
        {
            continue;
        }
        if (! turn.combination(operation_intersection, operation_union))
        {
            // ii may travel to itself
            continue;
        }

        for (int i = 0; i < 2; i++)
        {
            op_type& op = turn.operations[i];

            if (op.enriched.startable
                && op.operation == operation_intersection
                && op.enriched.get_next_turn_index() == turn_index)
            {
                // Self-turn i/u, i part traveling to itself. Discard it.
                // (alternatively it might be made unstartable - but the
                // intersection-operation may not be traveled anyway, and the
                // union-operation is not traveled at all in intersections
                // #case_recursive_boxes_77
                turn.discarded = true;
            }
        }
    }

}
=======

template <overlay_type OverlayType, operation_type OperationType>
struct discard_open_turns : discard_turns {};

// Handler for intersection
template <>
struct discard_open_turns<overlay_intersection, operation_intersection>
        : discard_self_intersection_turns<overlay_intersection> {};

// Handler for difference, with different meaning of 'within'
template <>
struct discard_open_turns<overlay_difference, operation_intersection>
        : discard_self_intersection_turns<overlay_difference> {};
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

}} // namespace detail::overlay
#endif //DOXYGEN_NO_DETAIL


}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_OVERLAY_HANDLE_SELF_TURNS_HPP
