// Boost.Geometry

<<<<<<< HEAD
// Copyright (c) 2017 Oracle and/or its affiliates.
=======
// Copyright (c) 2017-2019 Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_MULTI_POINT_GEOMETRY_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_MULTI_POINT_GEOMETRY_HPP


#include <boost/range.hpp>

#include <boost/geometry/algorithms/detail/disjoint/box_box.hpp>
#include <boost/geometry/algorithms/detail/disjoint/point_box.hpp>
#include <boost/geometry/algorithms/detail/expand_by_epsilon.hpp>
<<<<<<< HEAD
=======
#include <boost/geometry/algorithms/detail/partition.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/geometry/algorithms/detail/relate/result.hpp>
#include <boost/geometry/algorithms/detail/relate/topology_check.hpp>
#include <boost/geometry/algorithms/detail/within/point_in_geometry.hpp>
#include <boost/geometry/algorithms/envelope.hpp>

#include <boost/geometry/core/is_areal.hpp>
#include <boost/geometry/core/point_type.hpp>

#include <boost/geometry/geometries/box.hpp>

#include <boost/geometry/index/rtree.hpp>


namespace boost { namespace geometry
{

#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace relate
{

template
<
    typename Geometry,
<<<<<<< HEAD
=======
    typename EqPPStrategy,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typename Tag = typename tag<Geometry>::type
>
struct multi_point_geometry_eb
{
    template <typename MultiPoint>
    static inline bool apply(MultiPoint const& ,
<<<<<<< HEAD
                             detail::relate::topology_check<Geometry> const& )
=======
                             detail::relate::topology_check<Geometry, EqPPStrategy> const& )
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        return true;
    }
};

<<<<<<< HEAD
template <typename Geometry>
struct multi_point_geometry_eb<Geometry, linestring_tag>
=======
template <typename Geometry, typename EqPPStrategy>
struct multi_point_geometry_eb<Geometry, EqPPStrategy, linestring_tag>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
{
    template <typename Points>
    struct boundary_visitor
    {
        boundary_visitor(Points const& points)
            : m_points(points)
            , m_boundary_found(false)
        {}

        template <typename Point>
        struct find_pred
        {
            find_pred(Point const& point)
                : m_point(point)
            {}

            template <typename Pt>
            bool operator()(Pt const& pt) const
            {
<<<<<<< HEAD
                return detail::equals::equals_point_point(pt, m_point);
=======
                return detail::equals::equals_point_point(pt, m_point, EqPPStrategy());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }

            Point const& m_point;
        };

        template <typename Point>
        bool apply(Point const& boundary_point)
        {
            if (std::find_if(m_points.begin(), m_points.end(), find_pred<Point>(boundary_point)) == m_points.end())
            {
                m_boundary_found = true;
                return false;
            }
            return true;
        }

        bool result() const { return m_boundary_found; }

    private:
        Points const& m_points;
        bool m_boundary_found;
    };

    template <typename MultiPoint>
    static inline bool apply(MultiPoint const& multi_point,
<<<<<<< HEAD
                             detail::relate::topology_check<Geometry> const& tc)
=======
                             detail::relate::topology_check<Geometry, EqPPStrategy> const& tc)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        boundary_visitor<MultiPoint> visitor(multi_point);
        tc.for_each_boundary_point(visitor);
        return visitor.result();
    }
};

<<<<<<< HEAD
template <typename Geometry>
struct multi_point_geometry_eb<Geometry, multi_linestring_tag>
{
=======
template <typename Geometry, typename EqPPStrategy>
struct multi_point_geometry_eb<Geometry, EqPPStrategy, multi_linestring_tag>
{
    // TODO: CS-specific less compare strategy derived from EqPPStrategy
    typedef geometry::less<void, -1, typename EqPPStrategy::cs_tag> less_type;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    template <typename Points>
    struct boundary_visitor
    {
        boundary_visitor(Points const& points)
            : m_points(points)
            , m_boundary_found(false)
        {}

        template <typename Point>
        bool apply(Point const& boundary_point)
        {
<<<<<<< HEAD
            if (! std::binary_search(m_points.begin(), m_points.end(), boundary_point, geometry::less<>()))
=======
            if (! std::binary_search(m_points.begin(), m_points.end(), boundary_point, less_type()))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                m_boundary_found = true;
                return false;
            }
            return true;
        }

        bool result() const { return m_boundary_found; }

    private:
        Points const& m_points;
        bool m_boundary_found;
    };

    template <typename MultiPoint>
    static inline bool apply(MultiPoint const& multi_point,
<<<<<<< HEAD
                             detail::relate::topology_check<Geometry> const& tc)
=======
                             detail::relate::topology_check<Geometry, EqPPStrategy> const& tc)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {
        typedef typename boost::range_value<MultiPoint>::type point_type;
        typedef std::vector<point_type> points_type;
        points_type points(boost::begin(multi_point), boost::end(multi_point));
<<<<<<< HEAD
        std::sort(points.begin(), points.end(), geometry::less<>());
=======
        std::sort(points.begin(), points.end(), less_type());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        boundary_visitor<points_type> visitor(points);
        tc.for_each_boundary_point(visitor);
        return visitor.result();
    }
};

// SingleGeometry - Linear or Areal
template <typename MultiPoint, typename SingleGeometry, bool Transpose = false>
struct multi_point_single_geometry
{
    static const bool interruption_enabled = true;

    template <typename Result, typename Strategy>
    static inline void apply(MultiPoint const& multi_point,
                             SingleGeometry const& single_geometry,
                             Result & result,
                             Strategy const& strategy)
    {
        typedef typename point_type<SingleGeometry>::type point2_type;
        typedef model::box<point2_type> box2_type;
<<<<<<< HEAD
=======
        typedef typename Strategy::equals_point_point_strategy_type eq_pp_strategy_type;
        typedef typename Strategy::disjoint_point_box_strategy_type d_pb_strategy_type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        
        box2_type box2;
        geometry::envelope(single_geometry, box2, strategy.get_envelope_strategy());
        geometry::detail::expand_by_epsilon(box2);

        typedef typename boost::range_const_iterator<MultiPoint>::type iterator;
        for ( iterator it = boost::begin(multi_point) ; it != boost::end(multi_point) ; ++it )
        {
            if (! (relate::may_update<interior, interior, '0', Transpose>(result)
                || relate::may_update<interior, boundary, '0', Transpose>(result)
                || relate::may_update<interior, exterior, '0', Transpose>(result) ) )
            {
                break;
            }

            // The default strategy is enough for Point/Box
<<<<<<< HEAD
            if (detail::disjoint::disjoint_point_box(*it, box2))
=======
            if (detail::disjoint::disjoint_point_box(*it, box2, d_pb_strategy_type()))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                relate::set<interior, exterior, '0', Transpose>(result);
            }
            else
            {
                int in_val = detail::within::point_in_geometry(*it, single_geometry, strategy);

                if (in_val > 0) // within
                {
                    relate::set<interior, interior, '0', Transpose>(result);
                }
                else if (in_val == 0)
                {
                    relate::set<interior, boundary, '0', Transpose>(result);
                }
                else // in_val < 0 - not within
                {
                    relate::set<interior, exterior, '0', Transpose>(result);
                }
            }

            if ( BOOST_GEOMETRY_CONDITION(result.interrupt) )
            {
                return;
            }
        }

<<<<<<< HEAD
        typedef detail::relate::topology_check<SingleGeometry> tc_t;
=======
        typedef detail::relate::topology_check
            <
                SingleGeometry, eq_pp_strategy_type
            > tc_t;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        if ( relate::may_update<exterior, interior, tc_t::interior, Transpose>(result)
          || relate::may_update<exterior, boundary, tc_t::boundary, Transpose>(result) )
        {
            tc_t tc(single_geometry);

            if ( relate::may_update<exterior, interior, tc_t::interior, Transpose>(result)
              && tc.has_interior() )
            {
                // TODO: this is not true if a linestring is degenerated to a point
                // then the interior has topological dimension = 0, not 1
                relate::set<exterior, interior, tc_t::interior, Transpose>(result);
            }

            if ( relate::may_update<exterior, boundary, tc_t::boundary, Transpose>(result)
              && tc.has_boundary() )
            {
<<<<<<< HEAD
                if (multi_point_geometry_eb<SingleGeometry>::apply(multi_point, tc))
                    relate::set<exterior, boundary, tc_t::boundary, Transpose>(result);
=======
                if (multi_point_geometry_eb
                        <
                            SingleGeometry, eq_pp_strategy_type
                        >::apply(multi_point, tc))
                {
                    relate::set<exterior, boundary, tc_t::boundary, Transpose>(result);
                }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }

        relate::set<exterior, exterior, result_dimension<MultiPoint>::value, Transpose>(result);
    }
};


// MultiGeometry - Linear or Areal
// part of the algorithm calculating II and IB when no IE has to be calculated
// using partition()
template <typename MultiPoint, typename MultiGeometry, bool Transpose>
class multi_point_multi_geometry_ii_ib
{
<<<<<<< HEAD
=======
    template <typename ExpandPointStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct expand_box_point
    {
        template <typename Box, typename Point>
        static inline void apply(Box& total, Point const& point)
        {
<<<<<<< HEAD
            geometry::expand(total, point);
        }
    };

=======
            geometry::expand(total, point, ExpandPointStrategy());
        }
    };

    template <typename ExpandBoxStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct expand_box_box_pair
    {
        template <typename Box, typename BoxPair>
        static inline void apply(Box& total, BoxPair const& box_pair)
        {
<<<<<<< HEAD
            geometry::expand(total, box_pair.first);
        }
    };

=======
            geometry::expand(total, box_pair.first, ExpandBoxStrategy());
        }
    };

    template <typename DisjointPointBoxStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct overlaps_box_point
    {
        template <typename Box, typename Point>
        static inline bool apply(Box const& box, Point const& point)
        {
            // The default strategy is enough for Point/Box
<<<<<<< HEAD
            return ! detail::disjoint::disjoint_point_box(point, box);
        }
    };

=======
            return ! detail::disjoint::disjoint_point_box(point, box,
                                                          DisjointPointBoxStrategy());
        }
    };

    template <typename DisjointBoxBoxStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    struct overlaps_box_box_pair
    {
        template <typename Box, typename BoxPair>
        static inline bool apply(Box const& box, BoxPair const& box_pair)
        {
            // The default strategy is enough for Box/Box
<<<<<<< HEAD
            return ! detail::disjoint::disjoint_box_box(box_pair.first, box);
=======
            return ! detail::disjoint::disjoint_box_box(box_pair.first, box,
                                                        DisjointBoxBoxStrategy());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };

    template <typename Result, typename PtSegStrategy>
    class item_visitor_type
    {
<<<<<<< HEAD
    public:
        item_visitor_type(MultiGeometry const& multi_geometry,
                          detail::relate::topology_check<MultiGeometry> const& tc,
=======
        typedef typename PtSegStrategy::equals_point_point_strategy_type pp_strategy_type;
        typedef typename PtSegStrategy::disjoint_point_box_strategy_type d_pp_strategy_type;
        typedef detail::relate::topology_check<MultiGeometry, pp_strategy_type> topology_check_type;

    public:
        item_visitor_type(MultiGeometry const& multi_geometry,
                          topology_check_type const& tc,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                          Result & result,
                          PtSegStrategy const& strategy)
            : m_multi_geometry(multi_geometry)
            , m_tc(tc)
            , m_result(result)
            , m_strategy(strategy)
        {}

        template <typename Point, typename BoxPair>
        inline bool apply(Point const& point, BoxPair const& box_pair)
        {
            // The default strategy is enough for Point/Box
<<<<<<< HEAD
            if (! detail::disjoint::disjoint_point_box(point, box_pair.first))
=======
            if (! detail::disjoint::disjoint_point_box(point, box_pair.first, d_pp_strategy_type()))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            {
                typename boost::range_value<MultiGeometry>::type const&
                    single = range::at(m_multi_geometry, box_pair.second);

                int in_val = detail::within::point_in_geometry(point, single, m_strategy);

                if (in_val > 0) // within
                {
                    relate::set<interior, interior, '0', Transpose>(m_result);
                }
                else if (in_val == 0)
                {
                    if (m_tc.check_boundary_point(point))
                        relate::set<interior, boundary, '0', Transpose>(m_result);
                    else
                        relate::set<interior, interior, '0', Transpose>(m_result);
                }
            }

            if ( BOOST_GEOMETRY_CONDITION(m_result.interrupt) )
            {
                return false;
            }

            if (! (relate::may_update<interior, interior, '0', Transpose>(m_result)
                || relate::may_update<interior, boundary, '0', Transpose>(m_result) ) )
            {
                return false;
            }

            return true;
        }


    private:
        MultiGeometry const& m_multi_geometry;
<<<<<<< HEAD
        detail::relate::topology_check<MultiGeometry> const& m_tc;
=======
        topology_check_type const& m_tc;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        Result & m_result;
        PtSegStrategy const& m_strategy;
    };

public:
    typedef typename point_type<MultiPoint>::type point1_type;
    typedef typename point_type<MultiGeometry>::type point2_type;
    typedef model::box<point1_type> box1_type;
    typedef model::box<point2_type> box2_type;
    typedef std::pair<box2_type, std::size_t> box_pair_type;

    template <typename Result, typename Strategy>
    static inline void apply(MultiPoint const& multi_point,
                             MultiGeometry const& multi_geometry,
                             std::vector<box_pair_type> const& boxes,
<<<<<<< HEAD
                             detail::relate::topology_check<MultiGeometry> const& tc,
=======
                             detail::relate::topology_check
                                <
                                    MultiGeometry,
                                    typename Strategy::equals_point_point_strategy_type
                                > const& tc,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                             Result & result,
                             Strategy const& strategy)
    {
        item_visitor_type<Result, Strategy> visitor(multi_geometry, tc, result, strategy);

<<<<<<< HEAD
=======
        typedef expand_box_point
            <
                typename Strategy::expand_point_strategy_type
            > expand_box_point_type;
        typedef overlaps_box_point
            <
                typename Strategy::disjoint_point_box_strategy_type
            > overlaps_box_point_type;
        typedef expand_box_box_pair
            <
                typename Strategy::envelope_strategy_type::box_expand_strategy_type
            > expand_box_box_pair_type;
        typedef overlaps_box_box_pair
            <
                typename Strategy::disjoint_box_box_strategy_type
            > overlaps_box_box_pair_type;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        geometry::partition
            <
                box1_type
            >::apply(multi_point, boxes, visitor,
<<<<<<< HEAD
                     expand_box_point(),
                     overlaps_box_point(),
                     expand_box_box_pair(),
                     overlaps_box_box_pair());
=======
                     expand_box_point_type(),
                     overlaps_box_point_type(),
                     expand_box_box_pair_type(),
                     overlaps_box_box_pair_type());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

};

// MultiGeometry - Linear or Areal
// part of the algorithm calculating II, IB and IE
// using rtree
template <typename MultiPoint, typename MultiGeometry, bool Transpose>
struct multi_point_multi_geometry_ii_ib_ie
{
    typedef typename point_type<MultiPoint>::type point1_type;
    typedef typename point_type<MultiGeometry>::type point2_type;
    typedef model::box<point1_type> box1_type;
    typedef model::box<point2_type> box2_type;
    typedef std::pair<box2_type, std::size_t> box_pair_type;
    typedef std::vector<box_pair_type> boxes_type;
    typedef typename boxes_type::const_iterator boxes_iterator;

    template <typename Result, typename Strategy>
    static inline void apply(MultiPoint const& multi_point,
                             MultiGeometry const& multi_geometry,
                             std::vector<box_pair_type> const& boxes,
<<<<<<< HEAD
                             detail::relate::topology_check<MultiGeometry> const& tc,
                             Result & result,
                             Strategy const& strategy)
    {
        index::rtree<box_pair_type, index::rstar<4> > rt(boxes.begin(), boxes.end());
=======
                             detail::relate::topology_check
                                <
                                    MultiGeometry,
                                    typename Strategy::equals_point_point_strategy_type
                                > const& tc,
                             Result & result,
                             Strategy const& strategy)
    {
        typedef strategy::index::services::from_strategy
            <
                Strategy
            > index_strategy_from;
        typedef index::parameters
            <
                index::rstar<4>, typename index_strategy_from::type
            > index_parameters_type;
        index::rtree<box_pair_type, index_parameters_type>
            rtree(boxes.begin(), boxes.end(),
                  index_parameters_type(index::rstar<4>(), index_strategy_from::get(strategy)));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

        typedef typename boost::range_const_iterator<MultiPoint>::type iterator;
        for ( iterator it = boost::begin(multi_point) ; it != boost::end(multi_point) ; ++it )
        {
            if (! (relate::may_update<interior, interior, '0', Transpose>(result)
                || relate::may_update<interior, boundary, '0', Transpose>(result)
                || relate::may_update<interior, exterior, '0', Transpose>(result) ) )
            {
                return;
            }

            typename boost::range_value<MultiPoint>::type const& point = *it;

            boxes_type boxes_found;
<<<<<<< HEAD
            rt.query(index::intersects(point), std::back_inserter(boxes_found));
=======
            rtree.query(index::intersects(point), std::back_inserter(boxes_found));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            bool found_ii_or_ib = false;
            for (boxes_iterator bi = boxes_found.begin() ; bi != boxes_found.end() ; ++bi)
            {
                typename boost::range_value<MultiGeometry>::type const&
                    single = range::at(multi_geometry, bi->second);

                int in_val = detail::within::point_in_geometry(point, single, strategy);

                if (in_val > 0) // within
                {
                    relate::set<interior, interior, '0', Transpose>(result);
                    found_ii_or_ib = true;
                }
                else if (in_val == 0) // on boundary of single
                {
                    if (tc.check_boundary_point(point))
                        relate::set<interior, boundary, '0', Transpose>(result);
                    else
                        relate::set<interior, interior, '0', Transpose>(result);
                    found_ii_or_ib = true;
                }
            }

            // neither interior nor boundary found -> exterior
            if (found_ii_or_ib == false)
            {
                relate::set<interior, exterior, '0', Transpose>(result);
            }

            if ( BOOST_GEOMETRY_CONDITION(result.interrupt) )
            {
                return;
            }
        }
    }
};

// MultiGeometry - Linear or Areal
template <typename MultiPoint, typename MultiGeometry, bool Transpose = false>
struct multi_point_multi_geometry
{
    static const bool interruption_enabled = true;

    template <typename Result, typename Strategy>
    static inline void apply(MultiPoint const& multi_point,
                             MultiGeometry const& multi_geometry,
                             Result & result,
                             Strategy const& strategy)
    {
        typedef typename point_type<MultiGeometry>::type point2_type;
        typedef model::box<point2_type> box2_type;
        typedef std::pair<box2_type, std::size_t> box_pair_type;

<<<<<<< HEAD
=======
        typedef typename Strategy::equals_point_point_strategy_type eq_pp_strategy_type;

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        typename Strategy::envelope_strategy_type const
            envelope_strategy = strategy.get_envelope_strategy();
        
        std::size_t count2 = boost::size(multi_geometry);
        std::vector<box_pair_type> boxes(count2);
        for (std::size_t i = 0 ; i < count2 ; ++i)
        {
            geometry::envelope(range::at(multi_geometry, i), boxes[i].first, envelope_strategy);
            geometry::detail::expand_by_epsilon(boxes[i].first);
            boxes[i].second = i;
        }

<<<<<<< HEAD
        typedef detail::relate::topology_check<MultiGeometry> tc_t;
=======
        typedef detail::relate::topology_check<MultiGeometry, eq_pp_strategy_type> tc_t;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        tc_t tc(multi_geometry);

        if ( relate::may_update<interior, interior, '0', Transpose>(result)
          || relate::may_update<interior, boundary, '0', Transpose>(result)
          || relate::may_update<interior, exterior, '0', Transpose>(result) )
        {
            // If there is no need to calculate IE, use partition
            if (! relate::may_update<interior, exterior, '0', Transpose>(result) )
            {
                multi_point_multi_geometry_ii_ib<MultiPoint, MultiGeometry, Transpose>
                    ::apply(multi_point, multi_geometry, boxes, tc, result, strategy);
            }
            else // otherwise use rtree
            {
                multi_point_multi_geometry_ii_ib_ie<MultiPoint, MultiGeometry, Transpose>
                    ::apply(multi_point, multi_geometry, boxes, tc, result, strategy);
            }
        }

        if ( BOOST_GEOMETRY_CONDITION(result.interrupt) )
        {
            return;
        }

        if ( relate::may_update<exterior, interior, tc_t::interior, Transpose>(result)
          || relate::may_update<exterior, boundary, tc_t::boundary, Transpose>(result) )
        {
            if ( relate::may_update<exterior, interior, tc_t::interior, Transpose>(result)
              && tc.has_interior() )
            {
                // TODO: this is not true if a linestring is degenerated to a point
                // then the interior has topological dimension = 0, not 1
                relate::set<exterior, interior, tc_t::interior, Transpose>(result);
            }

            if ( relate::may_update<exterior, boundary, tc_t::boundary, Transpose>(result)
              && tc.has_boundary() )
            {
<<<<<<< HEAD
                if (multi_point_geometry_eb<MultiGeometry>::apply(multi_point, tc))
                    relate::set<exterior, boundary, tc_t::boundary, Transpose>(result);
=======
                if (multi_point_geometry_eb
                        <
                            MultiGeometry, eq_pp_strategy_type
                        >::apply(multi_point, tc))
                {
                    relate::set<exterior, boundary, tc_t::boundary, Transpose>(result);
                }
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }

        relate::set<exterior, exterior, result_dimension<MultiPoint>::value, Transpose>(result);
    }

};


template
<
    typename MultiPoint, typename Geometry,
    bool Transpose = false,
    bool isMulti = boost::is_same
                    <
                        typename tag_cast
                            <
                                typename tag<Geometry>::type, multi_tag
                            >::type,
                            multi_tag
                    >::value
>
struct multi_point_geometry
    : multi_point_single_geometry<MultiPoint, Geometry, Transpose>
{};

template <typename MultiPoint, typename Geometry, bool Transpose>
struct multi_point_geometry<MultiPoint, Geometry, Transpose, true>
    : multi_point_multi_geometry<MultiPoint, Geometry, Transpose>
{};


// transposed result of multi_point_geometry
template <typename Geometry, typename MultiPoint>
struct geometry_multi_point
{
    static const bool interruption_enabled = true;

    template <typename Result, typename Strategy>
    static inline void apply(Geometry const& geometry, MultiPoint const& multi_point,
                             Result & result, Strategy const& strategy)
    {
        multi_point_geometry<MultiPoint, Geometry, true>::apply(multi_point, geometry, result, strategy);
    }
};

}} // namespace detail::relate
#endif // DOXYGEN_NO_DETAIL

}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_RELATE_MULTI_POINT_GEOMETRY_HPP
